package io.grooviter.memento.cargo.projector.delivery.domain.events

import com.fasterxml.jackson.annotation.JsonProperty

import java.time.OffsetDateTime

class Delivered {
    @JsonProperty("aggregateId")
    UUID deliveryId

    @JsonProperty("createdAt")
    OffsetDateTime deliveredAt
}
