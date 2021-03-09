package io.grooviter.memento.model

import java.time.OffsetDateTime

class Event {
    UUID id = UUID.randomUUID()
    UUID aggregateId
    Integer version
    OffsetDateTime createdAt
}
