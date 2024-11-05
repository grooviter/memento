package memento.mn.jdbc

import com.fasterxml.jackson.databind.ObjectMapper
import memento.EventSerdePort
import memento.impl.JacksonEventSerde
import memento.model.Mappings
import io.micronaut.context.annotation.Factory

import jakarta.inject.Singleton

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
