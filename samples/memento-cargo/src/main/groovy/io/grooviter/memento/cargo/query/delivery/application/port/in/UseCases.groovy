package io.grooviter.memento.cargo.query.delivery.application.port.in

import groovy.transform.builder.Builder
import io.grooviter.memento.cargo.query.delivery.domain.Delivery

class UseCases {
    interface GetDeliveryStatusQuery {
        @Builder
        class Params {
            UUID deliveryId
        }

        Optional<Delivery> getDeliveryStatus(Params params)
    }
}
