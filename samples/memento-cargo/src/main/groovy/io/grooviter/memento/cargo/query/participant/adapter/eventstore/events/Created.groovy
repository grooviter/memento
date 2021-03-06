package io.grooviter.memento.cargo.query.participant.adapter.eventstore.events

import io.grooviter.memento.model.Event

import java.time.OffsetDateTime

class Created extends Event {
    UUID id
    String name
    String role
    OffsetDateTime createdAt
}
