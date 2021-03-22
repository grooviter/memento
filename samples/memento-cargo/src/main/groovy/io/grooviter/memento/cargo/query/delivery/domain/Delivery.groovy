package io.grooviter.memento.cargo.query.delivery.domain

import groovy.transform.Immutable

import java.time.OffsetDateTime

@Immutable
class Delivery {
    UUID id
    String status
    OffsetDateTime onRouteSince
    OffsetDateTime requestedAt
    OffsetDateTime deliveredAt
}
