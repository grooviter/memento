package memento.samples.cargo.query.common.adapter.eventstore

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
class ProjectorEventStoreFactory {

    @Singleton
    @QueryQualifier
    EventStore projectorEventStore(
        List<QueryMappingsContainer> queryMappingsContainerList,
        ObjectMapper objectMapper,
        EventStoragePort eventStoragePort,
        EventBusPort eventBusPort
    ) {
        List<Mappings> mappingsList =
                queryMappingsContainerList.collect { it.mappings }
        EventSerdePort eventSerdePort =
                new JacksonEventSerde(mappingsList, objectMapper)

        return Memento.builder()
            .serde(eventSerdePort)
            .eventStorage(eventStoragePort)
            .eventBus(eventBusPort)
            .build()
    }
}
