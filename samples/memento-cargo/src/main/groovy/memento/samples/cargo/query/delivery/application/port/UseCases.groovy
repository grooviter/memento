package memento.samples.cargo.query.delivery.application.port

import groovy.transform.builder.Builder
import memento.samples.cargo.query.delivery.domain.Delivery

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
