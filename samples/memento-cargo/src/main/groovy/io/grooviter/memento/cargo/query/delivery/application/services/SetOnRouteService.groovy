package io.grooviter.memento.cargo.query.delivery.application.services

import io.grooviter.memento.cargo.query.delivery.application.port.in.UseCases
import io.grooviter.memento.cargo.query.delivery.application.port.out.DeliveryQueryPorts
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class SetOnRouteService implements UseCases.SetOnRouteCommand {

    @Inject
    DeliveryQueryPorts.LoadDeliveryById loadDeliveryByid

    @Inject
    DeliveryQueryPorts.UpdateDeliveryPort updateDeliveryPort

    @Override
    @Transactional
    void setOnRoute(Params params) {
        loadDeliveryByid
            .findById(params.deliveryId)
            .map(delivery -> delivery.copyWith(status: 'ON_ROUTE', onRouteSince: params.since))
            .ifPresent(updateDeliveryPort::updateDelivery)
    }
}
