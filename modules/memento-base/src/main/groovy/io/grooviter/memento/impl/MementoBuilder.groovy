package io.grooviter.memento.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers
import io.grooviter.memento.EventBusPort
import io.grooviter.memento.EventStoragePort
import io.grooviter.memento.EventStore
import io.grooviter.memento.EventSerdePort
import io.grooviter.memento.model.Event
import io.grooviter.memento.EventStoreConfig
import io.grooviter.memento.model.Mappings

import java.util.function.Consumer

class MementoBuilder {

    private EventStoreConfig.EventStoreConfigBuilder configBuilder

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

    MementoBuilder serde(EventSerdePort serde) {
        this.configBuilder = this.configBuilder.serde(serde)
        return this
    }

    MementoBuilder jacksonSerde() {
        this.configBuilder = this.configBuilder.serde(new JacksonEventSerde())
        return this
    }

    MementoBuilder jacksonSerde(Mappings mappings) {
        this.configBuilder = this.configBuilder.serde(new JacksonEventSerde([mappings], null))
        return this
    }

    MementoBuilder jacksonSerde(Mappings mappings, ObjectMapper objectMapper) {
        this.configBuilder = this.configBuilder.serde(new JacksonEventSerde([mappings], objectMapper))
        return this
    }

    MementoBuilder onEvent(Consumer<Event> consumer) {
        this.eventBus(DefaultEventBus.create(consumer))
        return this
    }

    EventStore build() {
        EventStoreConfig config = this.configBuilder.build()

        assert config.eventStorage, "event-storage should be provided"
        assert config.serde, "event serialization mechanism should be provided"
        assert config.eventBus, "event bus mechanism should be provided"

        return new EventStoreImpl(config)
    }
}
