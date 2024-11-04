package io.grooviter.memento.cargo.command.participant.application.services

import io.grooviter.memento.cargo.command.participant.application.port.in.UseCases
import io.grooviter.memento.cargo.command.participant.application.port.out.RegistryPorts
import io.grooviter.memento.cargo.command.participant.domain.Participant

import jakarta.inject.Inject
import jakarta.inject.Singleton
import io.micronaut.transaction.annotation.Transactional

@Singleton
class RegisterParticipantService implements UseCases.RegisterParticipantCommand {

    @Inject
    RegistryPorts.SaveParticipantPort saveParticipantPort

    @Override
    @Transactional
    UUID registerParticipant(Params params) {
        Participant participant = Participant.register(params.name, params.role)
        saveParticipantPort.saveParticipant(participant)

        return participant.id
    }
}
