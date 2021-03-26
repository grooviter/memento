package io.grooviter.memento.cargo.projector.participant.domain

import java.time.OffsetDateTime

class Participant {
    UUID id
    String name
    String role
    OffsetDateTime createdAt
}
