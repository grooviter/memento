package io.grooviter.memento.impl.csv

import io.grooviter.memento.SerdePort
import io.grooviter.memento.model.Aggregate
import io.grooviter.memento.model.Event

import java.time.OffsetDateTime

class Mappers {

    static final String SEPARATOR_SPLIT = '\\|'
    static final String SEPARATOR_JOIN = '|'
    static final String NEWLINE = '\n'

    static Aggregate toAggregate(String row, SerdePort serdePort) {
        def (id, aggregateId, aggregateType, version, json, createdAt) = row.split(SEPARATOR_SPLIT)

        return serdePort.aggregateFromJSON("$aggregateType", "$json")
    }

    static Event toEvent(String row, SerdePort serdePort) {
        def (id, aggregateId, version, type, json, createdAt) = row.split(SEPARATOR_SPLIT)

        return serdePort.eventFromJSON("$type", "$json")
    }

    static String toSnapshotRow(Aggregate aggregate, SerdePort serdePort) {
        Map<String, ?> rowMap = [
            id: UUID.randomUUID(),
            aggregateId: aggregate.id,
            aggregateType: serdePort.resolveAlias(aggregate.getClass()),
            version: aggregate.version,
            json: serdePort.aggregateToJSON(aggregate),
            createdAt: OffsetDateTime.now()
        ]

        rowMap.values()*.toString().join(SEPARATOR_JOIN) + NEWLINE
    }

    static String toEventRow(Event event, SerdePort serdePort) {
        Map<String, ?> rowMap = [
            id: event.id,
            aggregateId: event.aggregateId,
            version: event.version,
            type: serdePort.resolveAlias(event.getClass()),
            json: serdePort.eventToJSON(event),
            createdAt: event.createdAt
        ]

        rowMap.values()*.toString().join(SEPARATOR_JOIN) + NEWLINE
    }
}
