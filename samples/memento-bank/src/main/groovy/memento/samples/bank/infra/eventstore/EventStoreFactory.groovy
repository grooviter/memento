package memento.samples.bank.infra.eventstore

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import memento.EventBusPort
import memento.EventStore
import memento.Memento
import memento.samples.bank.infra.Configuration
import memento.model.Mappings
import io.micronaut.context.annotation.Factory

import jakarta.inject.Singleton

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
