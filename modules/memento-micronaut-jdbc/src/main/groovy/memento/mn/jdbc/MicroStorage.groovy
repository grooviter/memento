package memento.mn.jdbc

import memento.EventStoragePort
import memento.EventSerdePort
import memento.mn.jdbc.impl.repository.EventRepository
import memento.mn.jdbc.impl.repository.SnapshotRepository
import memento.model.Aggregate
import memento.model.Event

import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.stream.Stream

/**
 * @since 0.1.0
 */
@Singleton
class MicroStorage implements EventStoragePort {

    /**
     * @since 0.1.0
     */
    @Inject
    EventRepository eventRepository

    /**
     * @since 0.1.0
     */
    @Inject
    SnapshotRepository snapshotRepository

    @Override
    void append(Event event, EventSerdePort serdePort) {
        this.eventRepository.append(memento.mn.jdbc.impl.Mappers.toEventEntity(serdePort, event))
    }

    @Override
    void snapshot(Aggregate aggregate, EventSerdePort serdePort) {
        this.snapshotRepository.save(memento.mn.jdbc.impl.Mappers.toSnapshotEntity(serdePort, aggregate))
    }

    @Override
    Optional<Aggregate> findByAggregateIdOrderByVersionDesc(UUID id, EventSerdePort serdePort) {
        return snapshotRepository.findByAggregateIdOrderByVersionDesc(id)
            .map(snp -> memento.mn.jdbc.impl.Mappers.toAggregate(serdePort, snp))
    }

    @Override
    Stream<Event> findAllByAggregateIdAndVersionGreaterThanOrderByVersion(UUID aggregateId, Integer version, EventSerdePort serdePort) {
        return eventRepository
            .findAllByAggregateIdAndVersionGreaterThanOrderByVersion(aggregateId, version)
            .map(eve -> memento.mn.jdbc.impl.Mappers.toEvent(serdePort, eve))
    }

    @Override
    Stream<Event> findAllByAggregateId(UUID aggregateId, EventSerdePort serdePort) {
        return eventRepository
            .findAllByAggregateIdOrderByVersion(aggregateId)
            .map(eve -> memento.mn.jdbc.impl.Mappers.toEvent(serdePort, eve))
    }

    @Override
    Stream<Event> findAllByAliases(String[] aliases, EventSerdePort serdePort) {
        return eventRepository
            .findAllByTypeIn(aliases)
            .map(eve -> memento.mn.jdbc.impl.Mappers.toEvent(serdePort, eve))
    }
}
