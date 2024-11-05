package memento.samples.cargo.query.delivery.application.services

import memento.samples.cargo.query.delivery.application.port.UseCases
import memento.samples.cargo.query.delivery.application.port.DeliveryQueryPorts
import memento.samples.cargo.query.delivery.domain.Delivery

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
