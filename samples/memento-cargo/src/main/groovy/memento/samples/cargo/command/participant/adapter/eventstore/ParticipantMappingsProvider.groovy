package memento.samples.cargo.command.participant.adapter.eventstore

import memento.samples.cargo.command.common.adapter.eventstore.CommandMappingsContainer
import memento.samples.cargo.command.participant.domain.Participant
import memento.samples.cargo.command.participant.domain.events.Registered
import memento.model.Mappings
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
