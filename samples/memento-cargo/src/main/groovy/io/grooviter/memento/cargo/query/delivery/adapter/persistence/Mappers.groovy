package io.grooviter.memento.cargo.query.delivery.adapter.persistence

import io.grooviter.memento.cargo.query.delivery.adapter.persistence.entity.DeliveryEntity
import io.grooviter.memento.cargo.query.delivery.domain.Delivery

class Mappers {

        static DeliveryEntity toEntity(Delivery delivery) {
            return new DeliveryEntity(
                    id: delivery.id,
                    status: delivery.status,
                    onRouteSince: delivery.onRouteSince,
                    requestedAt: delivery.requestedAt,
                    deliveredAt: delivery.deliveredAt
            )
        }

        static Delivery toDomain(DeliveryEntity delivery) {
            return new Delivery(
                    id: delivery.id,
                    status: delivery.status.toString(),
                    onRouteSince: delivery.onRouteSince,
                    requestedAt: delivery.requestedAt,
                    deliveredAt: delivery.deliveredAt
            )
        }
}
