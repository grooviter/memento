package io.grooviter.memento.cargo.projector.delivery.adapter.eventstore.events

import com.fasterxml.jackson.annotation.JsonProperty

import java.time.OffsetDateTime

class OnRoute {
    @JsonProperty("aggregateId")
    UUID deliveryId

    @JsonProperty("createdAt")
    OffsetDateTime onRouteSince
}
