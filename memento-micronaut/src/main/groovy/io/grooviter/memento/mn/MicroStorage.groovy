package io.grooviter.memento.mn

import io.grooviter.memento.EventStoragePort
import io.grooviter.memento.SerdePort
import io.grooviter.memento.mn.impl.Mappers
import io.grooviter.memento.mn.impl.repository.EventRepository
import io.grooviter.memento.mn.impl.repository.SnapshotRepository
import io.grooviter.memento.model.Aggregate
import io.grooviter.memento.model.Event

import javax.inject.Inject
import java.util.stream.Stream

/**
 * @since 0.1.0
 */
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
    void append(Event event, SerdePort serdePort) {
        this.eventRepository.append(Mappers.toEventEntity(serdePort, event))
    }

    @Override
    void snapshot(Aggregate aggregate, SerdePort serdePort) {
        this.snapshotRepository.save(Mappers.toSnapshotEntity(serdePort, aggregate))
    }

    @Override
    Optional<Aggregate> findByAggregateIdOrderByVersionDesc(UUID id, SerdePort serdePort) {
        return snapshotRepository.findByAggregateIdOrderByVersionDesc(id)
            .map(snp -> Mappers.toAggregate(serdePort, snp))
    }

    @Override
    Stream<Event> findAllByAggregateIdAndVersionGreaterThanOrderByVersion(UUID aggregateId, Integer version, SerdePort serdePort) {
        return eventRepository
            .findAllByAggregateIdAndVersionGreaterThanOrderByVersion(aggregateId, version)
            .map(eve -> Mappers.toEvent(serdePort, eve))
    }

    @Override
    Stream<Event> findAllByAggregateId(UUID aggregateId, SerdePort serdePort) {
        return eventRepository
            .findAllByAggregateId(aggregateId)
            .map(eve -> Mappers.toEvent(serdePort, eve))
    }
}
