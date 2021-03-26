package io.grooviter.memento.cargo.command.common.adapter.eventstore

import com.fasterxml.jackson.databind.ObjectMapper
import io.grooviter.memento.EventBusPort
import io.grooviter.memento.EventSerdePort
import io.grooviter.memento.EventStoragePort
import io.grooviter.memento.EventStore
import io.grooviter.memento.Memento
import io.grooviter.memento.cargo.command.delivery.domain.Delivery
import io.grooviter.memento.cargo.command.delivery.domain.events.Loaded
import io.grooviter.memento.cargo.command.delivery.domain.events.Received
import io.grooviter.memento.cargo.command.delivery.domain.events.Requested
import io.grooviter.memento.cargo.command.delivery.domain.events.Unloaded
import io.grooviter.memento.cargo.command.participant.domain.Participant
import io.grooviter.memento.cargo.command.participant.domain.events.Registered
import io.grooviter.memento.impl.JacksonEventSerde
import io.grooviter.memento.model.Mappings
import io.micronaut.context.annotation.Factory

import javax.inject.Singleton

@Factory
@SuppressWarnings('unused')
class CommandEventStoreFactory {

    @Singleton
    @Command
    Mappings commandMappings() {
        return Mappings.builder()
            .addMapping("DELIVERY", Delivery)
            .addMapping("DELIVERY_REQUESTED", Requested)
            .addMapping("DELIVERY_LOADED", Loaded)
            .addMapping("DELIVERY_UNLOADED", Unloaded)
            .addMapping("DELIVERY_RECEIVED", Received)
            .addMapping("PARTICIPANT", Participant)
            .addMapping("PARTICIPANT_REGISTERED", Registered)
            .build()
    }

    @Singleton
    @Command
    EventSerdePort commandEventSerdePort(
        @Command Mappings mappings,
        ObjectMapper objectMapper
    ) {
        return new JacksonEventSerde([mappings], objectMapper)
    }

    @Singleton
    @Command
    EventStore commandEventStore(
        @Command EventSerdePort eventSerdePort,
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
