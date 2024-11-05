package memento.samples.cargo.query.participant.adapter.eventstore.events

import memento.model.Event

import java.time.OffsetDateTime

class Created extends Event {
    UUID id
    String name
    String role
    OffsetDateTime createdAt
}
