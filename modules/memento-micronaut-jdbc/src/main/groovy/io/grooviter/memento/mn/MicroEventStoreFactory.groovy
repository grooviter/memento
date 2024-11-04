package io.grooviter.memento.mn

import io.grooviter.memento.EventBusPort
import io.grooviter.memento.EventStoragePort
import io.grooviter.memento.EventStore
import io.grooviter.memento.Memento
import io.grooviter.memento.EventSerdePort
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Value

import jakarta.inject.Singleton

@Factory
class MicroEventStoreFactory {

    @Singleton
    EventStore createConfig(
            EventStoragePort storage,
            EventBusPort eventBus,
            EventSerdePort serdePort,
            @Value('${memento.snapshot.threshold:10}') Integer snapshotThreshold
    ) {
        return Memento.builder()
            .eventStorage(storage)
            .eventBus(eventBus)
            .serde(serdePort)
            .snapshotThreshold(snapshotThreshold)
            .build()
    }
}
