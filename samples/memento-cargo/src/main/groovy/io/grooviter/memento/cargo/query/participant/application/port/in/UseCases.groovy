package io.grooviter.memento.cargo.query.participant.application.port.in

import io.grooviter.memento.cargo.query.participant.domain.Participant

class UseCases {

    interface ShowPaginatedParticipantsQuery {
        List<Participant> list()
    }
}
