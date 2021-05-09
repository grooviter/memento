package io.grooviter.memento

import io.grooviter.memento.model.Aggregate
import io.grooviter.memento.model.Event

import java.util.stream.Stream

interface EventStoragePort {

    void append(Event event, EventSerdePort serdePort)

    void snapshot(Aggregate aggregate, EventSerdePort serdePort)

    Optional<Aggregate> findByAggregateIdOrderByVersionDesc(UUID id, EventSerdePort serdePort)

    Stream<Event> findAllByAggregateIdAndVersionGreaterThanOrderByVersion(
            UUID aggregateId,
            Integer version,
            EventSerdePort serdePort)

    Stream<Event> findAllByAggregateId(UUID aggregateId, EventSerdePort serdePort)

    Stream<Event> findAllByAliases(String[] aliases, EventSerdePort serdePort)

}