package memento.samples.cargo.query.participant.adapter.persistence

import memento.samples.cargo.query.participant.domain.Participant

class Mappers {

    static Participant toParticipant(ParticipantEntity participant) {
        return new Participant(
            id: participant.id,
            name: participant.name,
            role: participant.role,
            createdAt: participant.createdAt
        )
    }

    static ParticipantEntity toEntity(Participant participant) {
        return new ParticipantEntity(
                id: participant.id,
                name: participant.name,
                role: participant.role,
                createdAt: participant.createdAt
        )
    }
}
