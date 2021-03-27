package io.grooviter.memento.cargo.query.participant.adapter.persistence

import io.grooviter.memento.cargo.query.participant.adapter.persistence.repository.ParticipantEntityRepository
import io.grooviter.memento.cargo.query.participant.application.port.out.ParticipantQueryPorts
import io.grooviter.memento.cargo.query.participant.domain.Participant

import javax.inject.Inject
import javax.inject.Singleton
import java.util.stream.Collectors
import java.util.stream.StreamSupport

@Singleton
class ParticipantPersistenceAdapter implements
    ParticipantQueryPorts.ListParticipantsPort,
    ParticipantQueryPorts.ParticipantCountPort,
    ParticipantQueryPorts.SaveParticipantPort {

    @Inject
    ParticipantEntityRepository participantEntityRepository

    @Override
    List<Participant> list() {
        return StreamSupport
            .stream(participantEntityRepository.findAll().spliterator(), false)
            .map(Mappers::toParticipant)
            .collect(Collectors.toList())
    }

    @Override
    void save(Participant participant) {
        participantEntityRepository.save(Mappers.toEntity(participant))
    }

    @Override
    int count() {
        return participantEntityRepository.count()
    }
}
