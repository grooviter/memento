package io.grooviter.memento.cargo.projector.participant.application.services

import io.grooviter.memento.cargo.projector.participant.application.port.in.UseCases
import io.grooviter.memento.cargo.projector.participant.application.port.out.ParticipantProjectorPorts
import io.grooviter.memento.cargo.projector.participant.domain.Participant

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveNewParticipantService implements UseCases.SaveNewParticipantCommand {

    @Inject
    ParticipantProjectorPorts.SaveParticipantPort saveParticipantPort

    @Override
    void save(Params params) {
        Participant participant =
            new Participant(
                id: params.participantId,
                name: params.name,
                createdAt: params.createdAt,
                role: params.role
            )

        saveParticipantPort.save(participant)
    }
}
