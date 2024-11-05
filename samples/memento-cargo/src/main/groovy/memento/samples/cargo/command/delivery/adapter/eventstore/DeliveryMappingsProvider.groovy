package memento.samples.cargo.command.delivery.adapter.eventstore


import memento.samples.cargo.command.common.adapter.eventstore.CommandMappingsContainer
import memento.samples.cargo.command.delivery.domain.Delivery
import memento.samples.cargo.command.delivery.domain.events.Loaded
import memento.samples.cargo.command.delivery.domain.events.Prepared
import memento.samples.cargo.command.delivery.domain.events.Received
import memento.samples.cargo.command.delivery.domain.events.Requested
import memento.samples.cargo.command.delivery.domain.events.Unloaded
import memento.model.Mappings
import io.micronaut.context.annotation.Factory

import jakarta.inject.Singleton

@Factory
class DeliveryMappingsProvider {

    @Singleton
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
