package io.grooviter.memento.mn

import io.grooviter.memento.EventStoragePort
import io.grooviter.memento.EventSerdePort
import io.grooviter.memento.mn.impl.Mappers
import io.grooviter.memento.mn.impl.repository.EventRepository
import io.grooviter.memento.mn.impl.repository.SnapshotRepository
import io.grooviter.memento.model.Aggregate
import io.grooviter.memento.model.Event

import javax.inject.Inject
import javax.inject.Singleton
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
        this.eventRepository.append(Mappers.toEventEntity(serdePort, event))
    }

    @Override
    void snapshot(Aggregate aggregate, EventSerdePort serdePort) {
        this.snapshotRepository.save(Mappers.toSnapshotEntity(serdePort, aggregate))
    }

    @Override
    Optional<Aggregate> findByAggregateIdOrderByVersionDesc(UUID id, EventSerdePort serdePort) {
        return snapshotRepository.findByAggregateIdOrderByVersionDesc(id)
            .map(snp -> Mappers.toAggregate(serdePort, snp))
    }

    @Override
    Stream<Event> findAllByAggregateIdAndVersionGreaterThanOrderByVersion(UUID aggregateId, Integer version, EventSerdePort serdePort) {
        return eventRepository
            .findAllByAggregateIdAndVersionGreaterThanOrderByVersion(aggregateId, version)
            .map(eve -> Mappers.toEvent(serdePort, eve))
    }

    @Override
    Stream<Event> findAllByAggregateId(UUID aggregateId, EventSerdePort serdePort) {
        return eventRepository
            .findAllByAggregateIdOrderByVersion(aggregateId)
            .map(eve -> Mappers.toEvent(serdePort, eve))
    }

    @Override
    Stream<Event> findAllByAliases(String[] aliases, EventSerdePort serdePort) {
        return eventRepository
            .findAllByTypeIn(aliases)
            .map(eve -> Mappers.toEvent(serdePort, eve))
    }
}
