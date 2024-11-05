package memento.samples.cargo.query.participant.application.services

import memento.samples.cargo.query.participant.application.port.UseCases
import memento.samples.cargo.query.participant.application.port.ParticipantQueryPorts
import memento.samples.cargo.query.participant.domain.Participant

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class ShowPaginatedParticipantsService implements UseCases.ShowPaginatedParticipantsQuery {

    @Inject
    ParticipantQueryPorts.ListParticipantsPort listParticipantsPort

    @Override
    List<Participant> list() {
        return listParticipantsPort.list()
    }
}
