package memento.samples.cargo.query.delivery.adapter.persistence.entity

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

import java.time.OffsetDateTime

@MappedEntity('cargo_delivery')
class DeliveryEntity {
    @Id
    UUID id

    DeliveryStatus deliveryStatus

    OffsetDateTime onRouteSince

    OffsetDateTime requestedAt

    OffsetDateTime deliveredAt
}
