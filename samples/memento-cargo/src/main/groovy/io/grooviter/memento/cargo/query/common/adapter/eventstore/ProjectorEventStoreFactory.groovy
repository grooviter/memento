package io.grooviter.memento.cargo.query.common.adapter.eventstore

import com.fasterxml.jackson.databind.ObjectMapper
import io.grooviter.memento.EventBusPort
import io.grooviter.memento.EventSerdePort
import io.grooviter.memento.EventStoragePort
import io.grooviter.memento.EventStore
import io.grooviter.memento.Memento
// TODO fix DI of list
import io.grooviter.memento.cargo.query.participant.adapter.eventstore.events.Created
import io.grooviter.memento.impl.JacksonEventSerde
import io.grooviter.memento.model.Mappings
import io.micronaut.context.annotation.Factory

import javax.inject.Singleton

@Factory
@SuppressWarnings('unused')
class ProjectorEventStoreFactory {

    @Singleton
    @Query
    Mappings projectorMappings() {
        return Mappings.builder()
            .addMapping('PARTICIPANT_REGISTERED', Created)
            .build()
    }

    @Singleton
    @Query
    EventSerdePort projectorEventSerdePort(
            @Query Mappings mappings,
            ObjectMapper objectMapper
    ) {
        return new JacksonEventSerde([mappings], objectMapper)
    }

    @Singleton
    @Query
    EventStore projectorEventStore(
            @Query EventSerdePort eventSerdePort,
            EventStoragePort eventStoragePort,
            EventBusPort eventBusPort
    ) {
        return Memento.builder()
        .serde(eventSerdePort)
        .eventStorage(eventStoragePort)
        .eventBus(eventBusPort)
        .build()
    }
}
