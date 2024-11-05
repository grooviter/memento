package memento.samples.cargo.query.participant.adapter.persistence

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

import java.time.OffsetDateTime

@MappedEntity('cargo_participant')
class ParticipantEntity {
    @Id
    UUID id
    String name
    String role
    OffsetDateTime createdAt
}
