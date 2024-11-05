package memento.samples.cargo.query.delivery.application.services

import memento.samples.cargo.query.delivery.application.port.UseCases
import memento.samples.cargo.query.delivery.application.port.DeliveryQueryPorts

import jakarta.inject.Inject
import jakarta.inject.Singleton
import io.micronaut.transaction.annotation.Transactional

@Singleton
class MarkAsDeliveredService implements UseCases.MarkAsDeliveredCommand {

    @Inject
    DeliveryQueryPorts.LoadDeliveryById loadDeliveryById

    @Inject
    DeliveryQueryPorts.UpdateDeliveryPort updateDeliveryPort

    @Override
    @Transactional
    void markAsDelivered(Params params) {
        loadDeliveryById.findById(params.deliveryId)
            .map(delivery -> delivery.copyWith(
                deliveredAt: params.deliveredAt,
                onRouteSince: null,
                status: 'DELIVERED'))
            .ifPresent(updateDeliveryPort::updateDelivery)
    }
}
