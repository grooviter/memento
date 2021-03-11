package io.grooviter.memento.bookkeeper.infra

import com.google.common.eventbus.EventBus
import com.google.inject.AbstractModule
import com.google.inject.Provides
import io.grooviter.memento.EventBusPort
import io.grooviter.memento.EventStore
import io.grooviter.memento.Memento
import io.grooviter.memento.bookkeeper.common.config.YamlConfigModule
import io.grooviter.memento.bookkeeper.infra.config.InfraConfig
import io.grooviter.memento.bookkeeper.infra.ebus.BookKeeperEventBus
import io.grooviter.memento.bookkeeper.projector.adapter.ebus.AccountCreationListener
import io.grooviter.memento.model.Mappings

class InfraModule extends AbstractModule {
    void configure() {
        install(new YamlConfigModule<InfraConfig>("infra.yml", InfraConfig))
    }

    @Provides
    EventStore getEventStore(
        Mappings mappings,
        EventBusPort eventBusPort,
        InfraConfig infraConfig
    ) {
        return Memento.builder()
            .groovySerde(mappings)
            .eventBus(eventBusPort)
            .csvStorage(infraConfig.events, infraConfig.snapshots)
            .snapshotThreshold(infraConfig.threshold)
            .build()
    }

    @Provides
    EventBusPort getEventStoreEventBus(EventBus eventBus) {
        return new BookKeeperEventBus(eventBus)
    }

    @Provides
    EventBus getEventBus() {
        // create an instance
        EventBus eventBus = new EventBus()

        // register listeners
        eventBus.register(new AccountCreationListener())

        // return event bus
        return eventBus
    }
}
