package io.grooviter.memento.cargo.projector.participant.adapter.persistence

import io.grooviter.memento.cargo.projector.participant.adapter.persistence.entity.ParticipantEntity
import io.grooviter.memento.cargo.projector.participant.domain.Participant

class Mappers {

    static Participant toDomain(ParticipantEntity participant) {
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
