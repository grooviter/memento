package memento.csv

import memento.EventSerdePort
import memento.model.Aggregate
import memento.model.Event

import java.time.OffsetDateTime

class Mappers {

    static final String SEPARATOR_SPLIT = '\\|'
    static final String SEPARATOR_JOIN = '|'
    static final String NEWLINE = '\n'

    static Aggregate toAggregate(String row, EventSerdePort serdePort) {
        def (id, aggregateId, aggregateType, version, json, createdAt) = row.split(SEPARATOR_SPLIT)

        return serdePort.aggregateFromJSON("$aggregateType", "$json")
    }

    static Event toEvent(String row, EventSerdePort serdePort) {
        def (id, aggregateId, version, type, json, createdAt) = row.split(SEPARATOR_SPLIT)

        return serdePort.eventFromJSON("$type", "$json")
    }

    static String toSnapshotRow(Aggregate aggregate, EventSerdePort serdePort) {
        Map<String, ?> rowMap = [
            id: UUID.randomUUID(),
            aggregateId: aggregate.id,
            aggregateType: serdePort.resolveAlias(aggregate.getClass()),
            version: aggregate.version,
            json: serdePort.aggregateToJSON(aggregate),
            createdAt: serdePort.objectToJSON(OffsetDateTime.now())
        ]

        rowMap.values()*.toString().join(SEPARATOR_JOIN) + NEWLINE
    }

    static String toEventRow(Event event, EventSerdePort serdePort) {
        Map<String, ?> rowMap = [
            id: event.id,
            aggregateId: event.aggregateId,
            version: event.version,
            type: serdePort.resolveAlias(event.getClass()),
            json: serdePort.eventToJSON(event),
            createdAt: serdePort.objectToJSON(event.createdAt)
        ]

        rowMap.values()*.toString().join(SEPARATOR_JOIN) + NEWLINE
    }
}
