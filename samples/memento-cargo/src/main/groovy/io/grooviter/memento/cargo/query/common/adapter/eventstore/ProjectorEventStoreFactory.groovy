package io.grooviter.memento.cargo.query.common.adapter.eventstore

import com.fasterxml.jackson.databind.ObjectMapper
import io.grooviter.memento.EventBusPort
import io.grooviter.memento.EventSerdePort
import io.grooviter.memento.EventStoragePort
import io.grooviter.memento.EventStore
import io.grooviter.memento.Memento
import io.grooviter.memento.impl.JacksonEventSerde
import io.grooviter.memento.model.Mappings
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
