package io.grooviter.memento.mn

import io.grooviter.memento.EventBusPort
import io.grooviter.memento.EventStoragePort
import io.grooviter.memento.EventStore
import io.grooviter.memento.SerdePort
import io.grooviter.memento.impl.EventStoreImpl
import io.grooviter.memento.model.EventStoreConfig
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Value

import javax.inject.Singleton

@Factory
class MicroEventStoreFactory {

    @Singleton
    EventStoreConfig createConfig(
        EventStoragePort storage,
        EventBusPort eventBus,
        SerdePort serdePort,
        @Value('${memento.snapshot.threshold:10}') Integer snapshotThreshold
    ) {
        return EventStoreConfig.builder()
            .withEventStorage(storage)
            .withEventBus(eventBus)
            .withSerde(serdePort)
            .withSnapshotThreshold(snapshotThreshold)
            .build()
    }

    @Singleton
    EventStore create(EventStoreConfig config) {
        return new EventStoreImpl(config)
    }
}
