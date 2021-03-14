package io.grooviter.memento.impl

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import io.grooviter.memento.SerdePort
import io.grooviter.memento.model.Aggregate
import io.grooviter.memento.model.Event
import io.grooviter.memento.model.Mappings

@Slf4j
class JacksonSerde implements SerdePort {

    private final Mappings mappings
    private final ObjectMapper mapper

    JacksonSerde(List<Mappings> mappings, ObjectMapper objectMapper) {
        this.mappings = new Mappings(mappings.collectMany { it.mappingList })
        this.mapper = objectMapper
    }

    JacksonSerde() {
        this.mappings = Mappings.builder().build()
        this.mapper = new ObjectMapper()
    }

    @Override
    String aggregateToJSON(Aggregate aggregate) {
        return this.mapper.writeValueAsString(aggregate)
    }

    @Override
    String eventToJSON(Event event) {
        return this.mapper.writeValueAsString(event)
    }

    @Override
    String objectToJSON(Object o) {
        return this.mapper.writeValueAsString(o)
    }

    @Override
    Aggregate aggregateFromJSON(String alias, String json) {
        Class clazz = this.mappings.resolveClass(alias)
        log.debug("clazz for $alias is ${clazz.simpleName}")
        return this.mapper.readValue(json, clazz)
    }

    @Override
    Event eventFromJSON(String alias, String json) {
        return this.mapper.readValue(json, this.mappings.resolveClass(alias))
    }

    @Override
    String resolveAlias(Class clazz) {
        String alias = this.mappings.resolveAlias(clazz)
        log.debug("alias for ${clazz.simpleName} is $alias")
        return alias
    }
}
