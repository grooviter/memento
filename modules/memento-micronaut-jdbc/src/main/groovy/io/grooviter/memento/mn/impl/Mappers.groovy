package io.grooviter.memento.mn.impl

import io.grooviter.memento.EventSerdePort
import io.grooviter.memento.mn.impl.entity.EventEntity
import io.grooviter.memento.mn.impl.entity.SnapshotEntity
import io.grooviter.memento.model.Aggregate
import io.grooviter.memento.model.Event

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