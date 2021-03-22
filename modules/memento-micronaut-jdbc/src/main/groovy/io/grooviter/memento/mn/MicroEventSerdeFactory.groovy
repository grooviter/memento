package io.grooviter.memento.mn

import com.fasterxml.jackson.databind.ObjectMapper
import io.grooviter.memento.EventSerdePort
import io.grooviter.memento.impl.JacksonEventSerde
import io.grooviter.memento.model.Mappings
import io.micronaut.context.annotation.Factory

import javax.inject.Singleton

@Factory
class MicroEventSerdeFactory {

    @Singleton
    EventSerdePort getJacksonSerde(
        ObjectMapper objectMapper,
        List<Mappings> mappings
    ) {
        return new JacksonEventSerde(mappings, objectMapper)
    }
}
