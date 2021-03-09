package io.grooviter.memento.impl

import groovy.json.JsonGenerator
import groovy.json.JsonSlurper
import groovy.transform.TupleConstructor
import io.grooviter.memento.SerdePort
import io.grooviter.memento.model.Aggregate
import io.grooviter.memento.model.Event
import io.grooviter.memento.model.Mappings

@TupleConstructor
class GroovySerde implements SerdePort {

    static final JsonGenerator JSON_SERIALIZER = new JsonGenerator.Options()
        .excludeNulls()
        .dateFormat('yyyy-MM-dd HH:mm:ss')
        .excludeFieldsByName("class", "eventList", "eventsLoaded", "nextVersion")
        .build()

    Mappings mappings = Mappings.builder().build()

    @Override
    String aggregateToJSON(Aggregate aggregate) {
        return JSON_SERIALIZER.toJson(aggregate)
    }

    @Override
    Aggregate aggregateFromJSON(String alias, String json) {
        Class<? extends Aggregate> clazz = mappings.resolveClass(alias)
        Object object = new JsonSlurper().parseText(json)

        object.id = toUUID(object.id)

        return object.asType(clazz) as Aggregate
    }

    @Override
    String eventToJSON(Event event) {
        return JSON_SERIALIZER.toJson(event)
    }

    @Override
    Event eventFromJSON(String alias, String json) {
        Class<? extends Event> clazz = mappings.resolveClass(alias)
        Object object = new JsonSlurper().parseText(json)

        object.id = toUUID(object.id)
        object.aggregateId = toUUID(object.aggregateId)

        return object.asType(clazz) as Event
    }

    private static UUID toUUID(Object value) {
        return value ? UUID.fromString("$value") : null
    }

    @Override
    String resolveAlias(Class clazz) {
        return this.mappings.resolveAlias(clazz)
    }
}
