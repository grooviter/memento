package memento.mn.jdbc

import memento.EventBusPort
import memento.EventStoragePort
import memento.EventStore
import memento.Memento
import memento.EventSerdePort
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
