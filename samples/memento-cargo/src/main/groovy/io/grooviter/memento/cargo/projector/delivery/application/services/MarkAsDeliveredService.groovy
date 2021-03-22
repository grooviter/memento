package io.grooviter.memento.cargo.projector.delivery.application.services

import io.grooviter.memento.cargo.projector.delivery.application.port.in.UseCases
import io.grooviter.memento.cargo.projector.delivery.application.port.out.DeliveryProjectorPorts

import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional

@Singleton
class MarkAsDeliveredService implements UseCases.MarkAsDeliveredCommand {

    @Inject
    DeliveryProjectorPorts.LoadDeliveryPort loadDeliveryPort

    @Inject
    DeliveryProjectorPorts.UpdateDeliveryPort updateDeliveryPort

    @Override
    @Transactional
    void markAsDelivered(Params params) {
        loadDeliveryPort.loadDelivery(params.deliveryId)
            .map(delivery -> delivery.copyWith(
                deliveredAt: params.deliveredAt,
                onRouteSince: null,
                status: 'DELIVERED'))
            .ifPresent(updateDeliveryPort::updateDelivery)
    }
}
