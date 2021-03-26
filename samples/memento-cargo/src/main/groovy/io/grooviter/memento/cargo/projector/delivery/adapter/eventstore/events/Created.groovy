package io.grooviter.memento.cargo.projector.delivery.adapter.eventstore.events

import com.fasterxml.jackson.annotation.JsonProperty

import java.time.OffsetDateTime

class Created {
    @JsonProperty("aggregateId")
    UUID deliveryId

    OffsetDateTime createdAt
}
