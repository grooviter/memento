package io.grooviter.memento.cargo.command.participant.adapter.persistence

import io.grooviter.memento.cargo.command.participant.domain.Participant
import io.grooviter.memento.cargo.command.participant.domain.events.Registered
import io.grooviter.memento.model.Mappings
import io.micronaut.context.annotation.Factory

import javax.inject.Singleton

//@Factory
class ParticipantMappingsFactory {

    //@Singleton
    Mappings getParticipantMappings() {
        println "==============>participant"
        return Mappings.builder()
            .addMapping("PARTICIPANT", Participant)
            .addMapping("PARTICIPANT_REGISTERED", Registered)
            .build()
    }
}
