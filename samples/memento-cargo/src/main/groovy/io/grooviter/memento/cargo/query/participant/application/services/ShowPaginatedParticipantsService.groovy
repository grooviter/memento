package io.grooviter.memento.cargo.query.participant.application.services

import io.grooviter.memento.cargo.query.participant.application.port.in.UseCases
import io.grooviter.memento.cargo.query.participant.application.port.out.ParticipantQueryPorts
import io.grooviter.memento.cargo.query.participant.domain.Participant

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
