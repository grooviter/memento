package io.grooviter.memento.cargo.query.delivery.application.services

import io.grooviter.memento.cargo.query.delivery.application.port.in.UseCases
import io.grooviter.memento.cargo.query.delivery.application.port.out.DeliveryQueryPorts
import io.grooviter.memento.cargo.query.delivery.domain.Delivery

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class GetDeliveryStatusService implements UseCases.GetDeliveryStatusQuery {

    @Inject
    DeliveryQueryPorts.LoadDeliveryById loadDeliveryById

    @Override
    Optional<Delivery> getDeliveryStatus(Params params) {
        return loadDeliveryById.findById(params.deliveryId)
    }
}
