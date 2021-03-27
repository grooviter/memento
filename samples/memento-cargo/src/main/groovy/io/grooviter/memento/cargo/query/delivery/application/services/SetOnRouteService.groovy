package io.grooviter.memento.cargo.query.delivery.application.services

import io.grooviter.memento.cargo.query.delivery.application.port.in.UseCases
import io.grooviter.memento.cargo.query.delivery.application.port.out.DeliveryQueryPorts

import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional

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
