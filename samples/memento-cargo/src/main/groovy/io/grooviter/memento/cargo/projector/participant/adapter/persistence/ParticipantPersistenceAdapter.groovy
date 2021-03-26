package io.grooviter.memento.cargo.projector.participant.adapter.persistence

import io.grooviter.memento.cargo.projector.participant.adapter.persistence.repository.ParticipantEntityRepository
import io.grooviter.memento.cargo.projector.participant.application.port.out.ParticipantProjectorPorts
import io.grooviter.memento.cargo.projector.participant.domain.Participant

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParticipantPersistenceAdapter implements
    ParticipantProjectorPorts.SaveParticipantPort,
    ParticipantProjectorPorts.ParticipantCountPort {

    @Inject
    ParticipantEntityRepository participantEntityRepository

    @Override
    void save(Participant participant) {
        participantEntityRepository.save(Mappers.toEntity(participant))
    }

    @Override
    int count() {
        return participantEntityRepository.count()
    }
}
