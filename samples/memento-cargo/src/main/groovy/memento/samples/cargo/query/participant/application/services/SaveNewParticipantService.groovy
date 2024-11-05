package memento.samples.cargo.query.participant.application.services

import memento.samples.cargo.query.participant.application.port.UseCases
import memento.samples.cargo.query.participant.application.port.ParticipantQueryPorts
import memento.samples.cargo.query.participant.domain.Participant

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class SaveNewParticipantService implements UseCases.SaveNewParticipantCommand {

    @Inject
    ParticipantQueryPorts.SaveParticipantPort saveParticipantPort

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
