package io.grooviter.memento.cargo.query.delivery.application.services

import io.grooviter.memento.cargo.query.delivery.application.port.in.UseCases
import io.grooviter.memento.cargo.query.delivery.application.port.out.DeliveryQueryPorts
import io.grooviter.memento.cargo.query.delivery.domain.Delivery

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDeliveryStatusService implements UseCases.GetDeliveryStatusQuery {

    @Inject
    DeliveryQueryPorts.LoadDeliveryById loadDeliveryById

    @Override
    Optional<Delivery> getDeliveryStatus(Params params) {
        return loadDeliveryById.findById(params.deliveryId)
    }
}
