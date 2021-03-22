package io.grooviter.memento.cargo.common.adapter.ebus

import io.grooviter.memento.cargo.command.delivery.domain.Delivery
import io.grooviter.memento.cargo.command.delivery.domain.events.Loaded
import io.grooviter.memento.cargo.command.delivery.domain.events.Prepared
import io.grooviter.memento.cargo.command.delivery.domain.events.Received
import io.grooviter.memento.cargo.command.delivery.domain.events.Requested
import io.grooviter.memento.cargo.command.delivery.domain.events.Unloaded
import io.grooviter.memento.cargo.command.participant.domain.Participant
import io.grooviter.memento.cargo.command.participant.domain.events.Registered
import io.grooviter.memento.model.Mappings
import io.micronaut.context.annotation.Factory

import javax.inject.Singleton

@Factory
class CommonMappingsFactory {

    // TODO raise issue to Micronaut factory bean creation for list from different factories
    @Singleton
    Mappings getParticipantMappings() {
        return Mappings.builder()
            .addMapping("PARTICIPANT", Participant)
            .addMapping("PARTICIPANT_REGISTERED", Registered)
            .addMapping("DELIVERY", Delivery)
            .addMapping("DELIVERY_REQUESTED", Requested)
            .addMapping("DELIVERY_PREPARED", Prepared)
            .addMapping("DELIVERY_LOADED", Loaded)
            .addMapping("DELIVERY_UNLOADED", Unloaded)
            .addMapping("DELIVERY_RECEIVED", Received)
            .build()
    }
}
