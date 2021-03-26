package io.grooviter.memento.cargo.command.participant.adapter.persistence

import io.grooviter.memento.EventStore
import io.grooviter.memento.cargo.command.common.adapter.eventstore.Command
import io.grooviter.memento.cargo.command.participant.application.port.out.RegistryPorts
import io.grooviter.memento.cargo.command.participant.domain.Participant

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegistryPersistenceAdapter implements RegistryPorts.SaveParticipantPort {

    @Inject
    @Command
    EventStore eventStore

    @Override
    void saveParticipant(Participant participant) {
        eventStore.save(participant)
    }
}
