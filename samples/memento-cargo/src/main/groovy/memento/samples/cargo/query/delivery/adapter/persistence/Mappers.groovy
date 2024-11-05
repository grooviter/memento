package memento.samples.cargo.query.delivery.adapter.persistence

import memento.samples.cargo.query.delivery.adapter.persistence.entity.DeliveryEntity
import memento.samples.cargo.query.delivery.adapter.persistence.entity.DeliveryStatus
import memento.samples.cargo.query.delivery.domain.Delivery

class Mappers {

        static DeliveryEntity toEntity(Delivery delivery) {
            return new DeliveryEntity(
                id: delivery.id,
                deliveryStatus: DeliveryStatus.valueOf(delivery.status),
                onRouteSince: delivery.onRouteSince,
                requestedAt: delivery.requestedAt,
                deliveredAt: delivery.deliveredAt
            )
        }

        static Delivery toDomain(DeliveryEntity delivery) {
            return new Delivery(
                id: delivery.id,
                status: delivery.deliveryStatus.toString(),
                onRouteSince: delivery.onRouteSince,
                requestedAt: delivery.requestedAt,
                deliveredAt: delivery.deliveredAt
            )
        }
}
