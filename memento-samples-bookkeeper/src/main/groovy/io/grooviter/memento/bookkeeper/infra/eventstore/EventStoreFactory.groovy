package io.grooviter.memento.bookkeeper.infra.eventstore

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
            .groovySerde(mappings)
            .eventBus(eventBusPort)
            .csvStorage(infraConfig.csv.events, infraConfig.csv.snapshots)
            .snapshotThreshold(infraConfig.snapshots.threshold)
            .build()
    }
}
