package io.grooviter.memento.cargo.projector.participant.adapter.persistence.entity

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

import java.time.OffsetDateTime

@MappedEntity('cargo_participant')
class ParticipantEntity {
    @Id
    UUID id

    String name, role

    OffsetDateTime createdAt
}
