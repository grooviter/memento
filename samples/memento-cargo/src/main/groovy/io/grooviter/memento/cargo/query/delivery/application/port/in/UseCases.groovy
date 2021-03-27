package io.grooviter.memento.cargo.query.delivery.application.port.in

import groovy.transform.builder.Builder
import io.grooviter.memento.cargo.query.delivery.domain.Delivery

import java.time.OffsetDateTime

class UseCases {
    interface GetDeliveryStatusQuery {
        @Builder
        class Params {
            UUID deliveryId
        }

        Optional<Delivery> getDeliveryStatus(Params params)
    }

    interface CreateDeliveryCommand {
        @Builder
        class Params {
            UUID deliveryId
            OffsetDateTime requestedAt
        }

        void create(Params params)
    }

    interface SetOnRouteCommand {
        @Builder
        class Params {
            UUID deliveryId
            OffsetDateTime since
        }

        void setOnRoute(Params params)
    }

    interface MarkAsDeliveredCommand {
        @Builder
        class Params {
            UUID deliveryId
            OffsetDateTime deliveredAt
        }

        void markAsDelivered(Params params)
    }
}
