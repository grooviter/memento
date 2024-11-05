package memento.samples.cargo.command.common.adapter.eventstore

import com.fasterxml.jackson.databind.ObjectMapper
import memento.EventBusPort
import memento.EventSerdePort
import memento.EventStoragePort
import memento.EventStore
import memento.Memento
import memento.impl.JacksonEventSerde
import memento.model.Mappings
import io.micronaut.context.annotation.Factory

import jakarta.inject.Singleton

@Factory
@SuppressWarnings('unused')
class CommandEventStoreFactory {

    @Singleton
    @CommandQualifier
    EventStore commandEventStore(
        List<CommandMappingsContainer> mappingsContainer,
        ObjectMapper objectMapper,
        EventStoragePort eventStoragePort,
        EventBusPort eventBusPort
    ) {
        List<Mappings> mappingList =
                mappingsContainer.collect { it.mappings }
        EventSerdePort eventSerdePort =
                new JacksonEventSerde(mappingList, objectMapper)

        return Memento.builder()
            .serde(eventSerdePort)
            .eventStorage(eventStoragePort)
            .eventBus(eventBusPort)
            .build()
    }
}
