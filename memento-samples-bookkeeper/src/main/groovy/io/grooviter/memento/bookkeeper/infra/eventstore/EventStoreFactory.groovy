package io.grooviter.memento.bookkeeper.infra.eventstore

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.grooviter.memento.EventBusPort
import io.grooviter.memento.EventStore
import io.grooviter.memento.Memento
import io.grooviter.memento.bookkeeper.infra.Configuration
import io.grooviter.memento.model.Mappings
import io.micronaut.context.annotation.Factory

import javax.inject.Singleton

@Factory
class EventStoreFactory {

    @Singleton
    EventStore getEventStore(
            Mappings mappings,
            EventBusPort eventBusPort,
            Configuration infraConfig
    ) {
        return Memento.builder()
            .jacksonSerde(mappings, jacksonDefaults())
            .eventBus(eventBusPort)
            .csvStorage(infraConfig.csv.events, infraConfig.csv.snapshots)
            .snapshotThreshold(infraConfig.snapshots.threshold)
            .build()
    }

    static ObjectMapper jacksonDefaults() {
        return new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }
}
