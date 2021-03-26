package io.grooviter.memento.cargo.query.participant.domain

import java.time.OffsetDateTime

class Participant {
    UUID id
    String name, role
    OffsetDateTime createdAt
}
