package io.grooviter.memento.cargo.projector.participant.adapter.eventstore

import io.grooviter.memento.cargo.projector.participant.adapter.eventstore.events.Created
import io.grooviter.memento.cargo.projector.participant.domain.Participant

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
