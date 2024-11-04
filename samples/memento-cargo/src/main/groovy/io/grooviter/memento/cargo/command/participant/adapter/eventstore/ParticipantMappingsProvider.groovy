package io.grooviter.memento.cargo.command.participant.adapter.eventstore

import io.grooviter.memento.cargo.command.common.adapter.eventstore.CommandMappingsContainer
import io.grooviter.memento.cargo.command.participant.domain.Participant
import io.grooviter.memento.cargo.command.participant.domain.events.Registered
import io.grooviter.memento.model.Mappings
import io.micronaut.context.annotation.Factory
import jakarta.inject.Provider
import jakarta.inject.Singleton

@Factory
class ParticipantMappingsProvider {

    @Singleton
    CommandMappingsContainer get() {
        Mappings mappings = Mappings.builder()
            .addMapping("PARTICIPANT", Participant)
            .addMapping("PARTICIPANT_REGISTERED", Registered)
            .build()

        return new CommandMappingsContainer(mappings: mappings)
    }
}
