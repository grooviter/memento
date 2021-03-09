package io.grooviter.memento.impl.memory

import io.grooviter.memento.SerdePort
import io.grooviter.memento.model.Aggregate
import io.grooviter.memento.model.Event

import java.time.OffsetDateTime

class Mappers {

    static Aggregate toAggregate(MemorySnapshotRow row, SerdePort serdePort) {
        return serdePort.aggregateFromJSON(row.aggregateType, row.json)
    }

    static MemorySnapshotRow toSnapshotRow(Aggregate aggregate, SerdePort serdePort) {
        return MemorySnapshotRow.builder()
            .id(UUID.randomUUID())
            .aggregateId(aggregate.id)
            .aggregateType(serdePort.resolveAlias(aggregate.getClass()))
            .version(aggregate.version)
            .json(serdePort.aggregateToJSON(aggregate))
            .createdAt(OffsetDateTime.now())
            .build()
    }

    static Event toEvent(MemoryEventRow row, SerdePort serdePort) {
        return serdePort.eventFromJSON(row.type, row.json)
    }

    static MemoryEventRow toEventRow(Event event, SerdePort serdePort) {
        return MemoryEventRow.builder()
            .id(event.id)
            .aggregateId(event.aggregateId)
            .version(event.version)
            .type(serdePort.resolveAlias(event.getClass()))
            .json(serdePort.eventToJSON(event))
            .createdAt(event.createdAt)
            .build()
    }
}
