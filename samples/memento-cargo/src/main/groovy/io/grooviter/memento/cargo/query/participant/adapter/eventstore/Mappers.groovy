package io.grooviter.memento.cargo.query.participant.adapter.eventstore


import io.grooviter.memento.cargo.query.participant.domain.Participant
import io.grooviter.memento.cargo.query.participant.adapter.eventstore.events.Created

class Mappers {

    static Participant toParticipant(Created created) {
        return new Participant(
            id: created.id,
            name: created.name,
            role: created.role,
            createdAt: created.createdAt
        )
    }
}
