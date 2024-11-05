package memento.samples.cargo.query.participant.adapter.eventstore


import memento.samples.cargo.query.participant.domain.Participant
import memento.samples.cargo.query.participant.adapter.eventstore.events.Created

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
