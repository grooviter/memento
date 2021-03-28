package io.grooviter.memento.cargo.command.participant.adapter.eventstore

import io.grooviter.memento.cargo.command.common.adapter.eventstore.CommandMappingsContainer
import io.grooviter.memento.cargo.command.participant.domain.Participant
import io.grooviter.memento.cargo.command.participant.domain.events.Registered
import io.grooviter.memento.model.Mappings

import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ParticipantMappingsProvider implements Provider<CommandMappingsContainer> {

    CommandMappingsContainer get() {
        Mappings mappings = Mappings.builder()
            .addMapping("PARTICIPANT", Participant)
            .addMapping("PARTICIPANT_REGISTERED", Registered)
            .build()

        return new CommandMappingsContainer(mappings: mappings)
    }
}
