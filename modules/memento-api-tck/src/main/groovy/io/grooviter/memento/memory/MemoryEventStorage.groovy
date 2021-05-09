package io.grooviter.memento.memory

import io.grooviter.memento.EventStoragePort
import io.grooviter.memento.EventSerdePort
import io.grooviter.memento.model.Aggregate
import io.grooviter.memento.model.Event

import java.util.concurrent.ConcurrentHashMap
import java.util.stream.Stream

class MemoryEventStorage implements EventStoragePort {

    private Map<UUID, List<MemoryEventRow>> events = new ConcurrentHashMap<>()
    private Map<UUID, List<MemorySnapshotRow>> snapshots = new ConcurrentHashMap<>()

    @Override
    void append(Event event, EventSerdePort serdePort) {
        events.get(event.aggregateId, []).add(Mappers.toEventRow(event, serdePort))
    }

    @Override
    void snapshot(Aggregate aggregate, EventSerdePort serdePort) {
        snapshots.get(aggregate.id, []).add(Mappers.toSnapshotRow(aggregate, serdePort))
    }

    @Override
    Optional<Aggregate> findByAggregateIdOrderByVersionDesc(UUID id, EventSerdePort serdePort) {
        return this.snapshots
            .get(id)
            .stream()
            .sorted( (row1, row2) -> row2.version - row1.version)
            .limit(1)
            .findFirst()
            .map(row -> Mappers.toAggregate(row, serdePort))
    }

    @Override
    Stream<Event> findAllByAggregateIdAndVersionGreaterThanOrderByVersion(UUID aggregateId, Integer version, EventSerdePort serdePort) {
        return this.events
            .get(aggregateId, [])
            .stream()
            .takeWhile(row -> row.version >= version)
            .map(row -> Mappers.toEvent(row, serdePort))
    }

    @Override
    Stream<Event> findAllByAggregateId(UUID aggregateId, EventSerdePort serdePort) {
        return this.events
            .get(aggregateId)
            .stream()
            .map(row -> Mappers.toEvent(row, serdePort))
    }

    @Override
    Stream<Event> findAllByAliases(String[] aliases, EventSerdePort serdePort) {
        return null
    }
}
