package memento.mn.jdbc.impl

import memento.EventSerdePort
import memento.mn.jdbc.impl.entity.EventEntity
import memento.mn.jdbc.impl.entity.SnapshotEntity
import memento.model.Aggregate
import memento.model.Event

/**
 * Utility class to convert from domain to entity objects
 *
 * @since 0.1.0
 */
class Mappers {

    static <T extends Event> T toEvent(EventSerdePort serdePort, EventEntity source) {
        return serdePort.eventFromJSON(source.type, source.json)
    }

    static <T extends Aggregate> T toAggregate(EventSerdePort serdePort, SnapshotEntity source) {
        return serdePort.aggregateFromJSON(source.aggregateType, source.json) as T
    }

    static EventEntity toEventEntity(EventSerdePort serdePort, Event event) {
        return new EventEntity(
            id: UUID.randomUUID(),
            aggregateId: event.aggregateId,
            version: event.version,
            type: serdePort.resolveAlias(event.getClass()),
            json: serdePort.eventToJSON(event)
        )
    }

    static SnapshotEntity toSnapshotEntity(EventSerdePort serdePort, Aggregate aggregate) {
        return new SnapshotEntity(
            id: UUID.randomUUID(),
            aggregateId: aggregate.id,
            aggregateType: serdePort.resolveAlias(aggregate.getClass()),
            json: serdePort.aggregateToJSON(aggregate),
            version: aggregate.version
        )
    }
}