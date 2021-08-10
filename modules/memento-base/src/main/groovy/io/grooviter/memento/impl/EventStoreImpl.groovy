package io.grooviter.memento.impl


import groovy.util.logging.Slf4j
import io.grooviter.memento.EventBusPort
import io.grooviter.memento.EventStoragePort
import io.grooviter.memento.EventStore
import io.grooviter.memento.EventSerdePort
import io.grooviter.memento.model.Aggregate
import io.grooviter.memento.model.Event
import io.grooviter.memento.EventStoreConfig
import io.grooviter.memento.model.SnapshotEvent

import java.util.function.Supplier
import java.util.stream.Stream

@Slf4j
class EventStoreImpl implements EventStore {

    private EventStoragePort eventStorePort
    private EventBusPort eventBusPort
    private EventSerdePort serdePort
    private int snapshotThreshold

    EventStoreImpl(EventStoreConfig config) {
        this.eventStorePort = config.eventStorage
        this.snapshotThreshold = config.snapshotThreshold
        this.serdePort = config.serde
        this.eventBusPort = config.eventBus
        this.snapshotThreshold = config.snapshotThreshold
    }

    @Override
    void append(Event event) {
        log.debug("appending [${event.getClass().simpleName}] to memento")
        this.eventStorePort.append(event, serdePort)

        log.debug("notifying new event persisted")
        this.eventBusPort.publish(event, serdePort)
    }

    @Override
    Stream<Event> listEvents(UUID aggregateId, Integer version) {
        Supplier<Stream<Event>> supplier = supplyListEvents(aggregateId, version + 1)
        Stream<Event> loadedEvents = supplier.get()

        if (log.isDebugEnabled()) {
            Stream<Event> debugEvents = supplier.get()
            log.debug("loaded ${debugEvents.count()} events for aggregate $aggregateId from version $version")
        }

        return loadedEvents
    }

    private Supplier<Stream<Event>> supplyListEvents(UUID aggregateId, Integer version) {
        return () -> eventStorePort
                .findAllByAggregateIdAndVersionGreaterThanOrderByVersion(aggregateId, version, serdePort)
    }

    @Override
    Stream<Event> listEvents(UUID aggregateId) {
        log.debug("loading all events for aggregate $aggregateId")
        return eventStorePort.findAllByAggregateId(aggregateId, serdePort)
    }

    @Override
    Stream<Event> listEvents(String[] aliases) {
        return eventStorePort.findAllByAliases(aliases, serdePort)
    }

    @Override
    public <T extends Aggregate> Optional<T> load(UUID aggregateId, Class<T> aggregateType) {
        log.debug("loading last aggregate snapshot for ${aggregateId}")
        Supplier<Optional<T>> fromStream = loadFromStream(aggregateType, aggregateId)
        Supplier<Optional<T>> fromSnapshot = loadFromSnapshot(aggregateId)
        Optional<T> loaded = fromSnapshot.get() | fromStream

        return loaded.map { it.asSnapshot() }
    }

    /**
     * Loads aggregate from last snapshot and then it loads remaining
     * events from the event stream
     *
     * @param type the type of the aggregate
     * @param id the id of the aggregate
     * @return an updated representation of the {@link Aggregate}
     */
    private <T extends Aggregate> Supplier<Optional<T>> loadFromSnapshot(UUID id) {
        return () -> eventStorePort
            .findByAggregateIdOrderByVersionDesc(id, serdePort)
        // TODO missing check latest event and load events from snapshot version forwards
            .map((Aggregate agg) -> agg.applyEvents(this.listEvents(agg.id, agg.version)))
    }

    /**
     * Loads aggregate from the beginning of the event stream
     * @param type the type of the aggregate
     * @param id the id of the aggregate
     * @return an updated representation of the {@link Aggregate}
     */
    private <T extends Aggregate> Supplier<Optional<T>> loadFromStream(Class<T> type, UUID id) {
        return () -> Optional
            .ofNullable(type.declaredConstructors[0]?.newInstance() as Aggregate)
            .map(agg -> agg.tap { it.id = id })
            .map(agg -> agg.applyEvents(this.listEvents(id)))
            .map(agg -> agg.asSnapshot())
    }

    @Override
    void save(Aggregate aggregate) {
        aggregate.eventList.each(this::append)
        snapshot(aggregate)
    }

    @Override
    void snapshot(Aggregate aggregate) {
        // TODO this would only create ONE snapshot PER EVENT from THIS POINT
        // TODO if the number of eventsLoaded + the number of events since last snapshot version >= snapshotThreshold
        // TODO THEN do snapshot

        int aggregateVersion = aggregate.version
        int snapshotVersion = this
            .eventStorePort
            .findByAggregateIdOrderByVersionDesc(aggregate.id, this.serdePort)
            .map(agg -> agg.version)
            .orElse(0)

        int delta = aggregateVersion - snapshotVersion

        if (this.snapshotThreshold > delta) {
            log.debug("snapshot creation skipped")
            return
        }

        log.debug("creating new snapshot [type: '${aggregate.getClass().simpleName}' -- id: '${aggregate.id}']")
        this.eventStorePort.snapshot(aggregate, serdePort)

        SnapshotEvent snapshotEvent =
                new SnapshotEvent(aggregateId: aggregate.id, aggregateType: aggregate.getClass())

        log.debug("notifying new snapshot created")
        this.eventBusPort.publishAsync(snapshotEvent, serdePort)
    }
}
