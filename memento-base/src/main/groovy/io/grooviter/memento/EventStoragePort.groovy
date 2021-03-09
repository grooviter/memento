package io.grooviter.memento

import io.grooviter.memento.model.Aggregate
import io.grooviter.memento.model.Event

import java.util.stream.Stream

interface EventStoragePort {

    void append(Event event, SerdePort serdePort)

    void snapshot(Aggregate aggregate, SerdePort serdePort)

    Optional<Aggregate> findByAggregateIdOrderByVersionDesc(UUID id, SerdePort serdePort)

    Stream<Event> findAllByAggregateIdAndVersionGreaterThanOrderByVersion(UUID aggregateId, Integer version, SerdePort serdePort)

    Stream<Event> findAllByAggregateId(UUID aggregateId, SerdePort serdePort)
}