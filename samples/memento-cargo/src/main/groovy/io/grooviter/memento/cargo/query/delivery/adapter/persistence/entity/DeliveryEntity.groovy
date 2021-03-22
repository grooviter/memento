package io.grooviter.memento.cargo.query.delivery.adapter.persistence.entity

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.MappedProperty

import java.time.OffsetDateTime

@MappedEntity('cargo_delivery')
class DeliveryEntity {
    @Id
    UUID id

    @MappedProperty('delivery_status')
    String status

    OffsetDateTime onRouteSince

    OffsetDateTime requestedAt

    OffsetDateTime deliveredAt
}
