package io.grooviter.memento.cargo.command.delivery.adapter.eventstore


import io.grooviter.memento.cargo.command.common.adapter.eventstore.CommandMappingsContainer
import io.grooviter.memento.cargo.command.delivery.domain.Delivery
import io.grooviter.memento.cargo.command.delivery.domain.events.Loaded
import io.grooviter.memento.cargo.command.delivery.domain.events.Prepared
import io.grooviter.memento.cargo.command.delivery.domain.events.Received
import io.grooviter.memento.cargo.command.delivery.domain.events.Requested
import io.grooviter.memento.cargo.command.delivery.domain.events.Unloaded
import io.grooviter.memento.model.Mappings
import io.micronaut.context.annotation.Factory

import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class DeliveryMappingsProvider implements Provider<CommandMappingsContainer> {

    CommandMappingsContainer get() {
        Mappings mappings = Mappings.builder()
            .addMapping("DELIVERY", Delivery)
            .addMapping("DELIVERY_REQUESTED", Requested)
            .addMapping("DELIVERY_PREPARED", Prepared)
            .addMapping("DELIVERY_LOADED", Loaded)
            .addMapping("DELIVERY_UNLOADED", Unloaded)
            .addMapping("DELIVERY_RECEIVED", Received)
            .build()

        return new CommandMappingsContainer(mappings: mappings)
    }
}
