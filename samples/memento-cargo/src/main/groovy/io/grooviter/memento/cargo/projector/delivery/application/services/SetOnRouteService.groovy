package io.grooviter.memento.cargo.projector.delivery.application.services

import io.grooviter.memento.cargo.projector.delivery.application.port.in.UseCases
import io.grooviter.memento.cargo.projector.delivery.application.port.out.DeliveryProjectorPorts

import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional

@Singleton
class SetOnRouteService implements UseCases.SetOnRouteCommand {

    @Inject
    DeliveryProjectorPorts.LoadDeliveryPort loadDeliveryPort

    @Inject
    DeliveryProjectorPorts.UpdateDeliveryPort updateDeliveryPort

    @Override
    @Transactional
    void setOnRoute(Params params) {
        loadDeliveryPort
            .loadDelivery(params.deliveryId)
            .map(delivery -> delivery.copyWith(status: 'ON_ROUTE', onRouteSince: params.since))
            .ifPresent(updateDeliveryPort::updateDelivery)
    }
}
