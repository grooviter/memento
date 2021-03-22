package io.grooviter.memento.cargo.command.delivery.adapter.persistence

import io.grooviter.memento.cargo.command.delivery.domain.Delivery
import io.grooviter.memento.cargo.command.delivery.domain.events.Loaded
import io.grooviter.memento.cargo.command.delivery.domain.events.Received
import io.grooviter.memento.cargo.command.delivery.domain.events.Requested
import io.grooviter.memento.cargo.command.delivery.domain.events.Unloaded
import io.grooviter.memento.model.Mappings
import io.micronaut.context.annotation.Factory

import javax.inject.Singleton

//@Factory
class DeliveryMappingsFactory {

    //@Singleton
    Mappings getDeliveryMappings() {
        println "==============>delivery"
        return Mappings.builder()
            .addMapping("DELIVERY", Delivery)
            .addMapping("DELIVERY_REQUESTED", Requested)
            .addMapping("DELIVERY_LOADED", Loaded)
            .addMapping("DELIVERY_UNLOADED", Unloaded)
            .addMapping("DELIVERY_RECEIVED", Received)
            .build()
    }
}
