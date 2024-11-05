package memento.samples.cargo.command.participant.adapter.persistence

import memento.EventStore
import memento.samples.cargo.command.common.adapter.eventstore.CommandQualifier
import memento.samples.cargo.command.participant.application.port.out.RegistryPorts
import memento.samples.cargo.command.participant.domain.Participant

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class RegistryPersistenceAdapter implements RegistryPorts.SaveParticipantPort {

    @Inject
    @CommandQualifier
    EventStore eventStore

    @Override
    void saveParticipant(Participant participant) {
        eventStore.save(participant)
    }
}
