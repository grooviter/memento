package io.grooviter.memento.mn

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import io.grooviter.memento.SerdePort
import io.grooviter.memento.model.Aggregate
import io.grooviter.memento.model.Event
import io.grooviter.memento.model.Mappings

import javax.inject.Inject
import javax.inject.Singleton

@Slf4j
@Singleton
class MicroSerde implements SerdePort {

    private final Mappings mappings
    private final ObjectMapper mapper

    @Inject
    MicroSerde(List<Mappings> mappings, ObjectMapper objectMapper) {
        this.mappings = new Mappings(mappings.collectMany { it.mappingList })
        this.mapper = objectMapper
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
