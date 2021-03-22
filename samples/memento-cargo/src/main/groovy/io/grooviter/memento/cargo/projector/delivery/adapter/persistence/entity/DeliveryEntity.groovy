package io.grooviter.memento.cargo.projector.delivery.adapter.persistence.entity

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedProperty
import io.micronaut.data.annotation.MappedEntity

import java.time.OffsetDateTime

@MappedEntity('cargo_delivery')
class DeliveryEntity {
    @Id
    UUID id

    @MappedProperty('delivery_status')
    DeliveryStatus status

    OffsetDateTime onRouteSince

    OffsetDateTime requestedAt

    OffsetDateTime deliveredAt
}
