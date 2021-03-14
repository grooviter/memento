package io.grooviter.memento.model

import io.grooviter.memento.EventBusPort
import io.grooviter.memento.EventStoragePort
import io.grooviter.memento.SerdePort
import groovy.transform.builder.Builder
import groovy.transform.builder.ExternalStrategy
import io.grooviter.memento.impl.DefaultEventBus
import io.grooviter.memento.impl.JacksonSerde
import io.grooviter.memento.impl.memory.MemoryEventStorage

class EventStoreConfig {

    EventStoragePort eventStorage
    EventBusPort eventBus
    SerdePort serde
    int snapshotThreshold

    @Builder(builderStrategy=ExternalStrategy, forClass=EventStoreConfig)
    static class EventStoreConfigBuilder {
        EventStoreConfigBuilder() {
            this.eventStorage(new MemoryEventStorage())
            this.eventBus(DefaultEventBus.create((Event event) -> { /* DOES NOTHING */ }))
            this.serde(new JacksonSerde())
            this.snapshotThreshold(20)
        }
    }
}
