package memento.memory

import memento.EventSerdePort
import memento.model.Aggregate
import memento.model.Event

import java.time.OffsetDateTime

class Mappers {

    static Aggregate toAggregate(MemorySnapshotRow row, EventSerdePort serdePort) {
        return serdePort.aggregateFromJSON(row.aggregateType, row.json)
    }

    static MemorySnapshotRow toSnapshotRow(Aggregate aggregate, EventSerdePort serdePort) {
        return MemorySnapshotRow.builder()
            .id(UUID.randomUUID())
            .aggregateId(aggregate.id)
            .aggregateType(serdePort.resolveAlias(aggregate.getClass()))
            .version(aggregate.version)
            .json(serdePort.aggregateToJSON(aggregate))
            .createdAt(OffsetDateTime.now())
            .build()
    }

    static Event toEvent(MemoryEventRow row, EventSerdePort serdePort) {
        return serdePort.eventFromJSON(row.type, row.json)
    }

    static MemoryEventRow toEventRow(Event event, EventSerdePort serdePort) {
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
