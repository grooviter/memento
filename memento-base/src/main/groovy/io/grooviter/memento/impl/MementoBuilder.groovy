package io.grooviter.memento.impl

import com.fasterxml.jackson.databind.ObjectMapper
import io.grooviter.memento.EventBusPort
import io.grooviter.memento.EventStoragePort
import io.grooviter.memento.EventStore
import io.grooviter.memento.SerdePort
import io.grooviter.memento.impl.csv.CsvEventStorage
import io.grooviter.memento.impl.memory.MemoryEventStorage
import io.grooviter.memento.model.Event
import io.grooviter.memento.model.EventStoreConfig
import io.grooviter.memento.model.Mappings

import java.util.function.Consumer

class MementoBuilder {

    private EventStoreConfig.EventStoreConfigBuilder configBuilder
    private Consumer<Event> onEventHandler

    MementoBuilder() {
        this.configBuilder = new EventStoreConfig.EventStoreConfigBuilder()
    }

    MementoBuilder eventStorage(EventStoragePort storage) {
        this.configBuilder = this.configBuilder.eventStorage(storage)
        return this
    }

    MementoBuilder eventBus(EventBusPort eventBus) {
        this.configBuilder = this.configBuilder.eventBus(eventBus)
        return this
    }

    MementoBuilder snapshotThreshold(Integer threshold) {
        this.configBuilder = this.configBuilder.snapshotThreshold(threshold)
        return this
    }

    MementoBuilder serde(SerdePort serde) {
        this.configBuilder = this.configBuilder.serde(serde)
        return this
    }

    MementoBuilder jacksonSerde() {
        this.configBuilder = this.configBuilder.serde(new JacksonSerde([], new ObjectMapper()))
        return this
    }

    MementoBuilder jacksonSerde(Mappings mappings) {
        this.configBuilder = this.configBuilder.serde(new JacksonSerde([mappings], new ObjectMapper()))
        return this
    }

    MementoBuilder jacksonSerde(Mappings mappings, ObjectMapper objectMapper) {
        this.configBuilder = this.configBuilder.serde(new JacksonSerde([mappings], objectMapper))
        return this
    }

    MementoBuilder memoryStorage() {
        this.configBuilder = this.configBuilder.eventStorage(new MemoryEventStorage())
        return this
    }

    MementoBuilder csvStorage(File events, File snapshots) {
        CsvEventStorage csvEventStorage = CsvEventStorage.create(events, snapshots)
        this.configBuilder = this.configBuilder.eventStorage(csvEventStorage)
        return this
    }

    MementoBuilder csvStorage(String eventsPath, String snapshotsPath) {
        CsvEventStorage csvEventStorage = CsvEventStorage.create(new File(eventsPath), new File(snapshotsPath))
        this.configBuilder = this.configBuilder.eventStorage(csvEventStorage)
        return this
    }

    MementoBuilder onEvent(Consumer<Event> consumer) {
        this.onEventHandler = consumer
        return this
    }

    EventStore build() {
        if (this.onEventHandler) {
            this.configBuilder.eventBus(DefaultEventBus.create(this.onEventHandler))
        }

        return new EventStoreImpl(this.configBuilder.build())
    }
}
