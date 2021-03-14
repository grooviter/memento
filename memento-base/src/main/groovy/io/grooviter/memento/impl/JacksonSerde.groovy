package io.grooviter.memento.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
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
        this.mappings = Optional
            .ofNullable(mappings)
            .map(mappings1 -> mappings1.collectMany { it.mappingList })
            .map(Mappings::new)
            .orElse(Mappings.builder().build())

        this.mapper = Optional
            .ofNullable(objectMapper)
            .orElse(defaultObjectMapper())
    }

    JacksonSerde() {
        this.mappings = Mappings.builder().build()
        this.mapper = defaultObjectMapper()
    }

    private static ObjectMapper defaultObjectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
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
