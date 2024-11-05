package memento.samples.cargo.command.delivery.application.services

import memento.samples.cargo.command.delivery.application.port.UseCases
import memento.samples.cargo.command.delivery.application.port.DeliveryPorts
import io.micronaut.retry.annotation.Retryable

import jakarta.inject.Inject
import jakarta.inject.Singleton
import io.micronaut.transaction.annotation.Transactional

@Singleton
class UnloadDeliveryService implements UseCases.UnloadFromTrailerCommand {

    @Inject
    DeliveryPorts.LoadDeliveryPort loadDeliveryPort

    @Inject
    DeliveryPorts.SaveDeliveryPort saveDeliveryPort

    @Override
    @Retryable
    @Transactional
    void unloaded(Params params) {
        loadDeliveryPort.loadById(params.deliveryId)
            .map(delivery -> delivery.unload(params.unloadedBy, params.comments))
            .ifPresent(saveDeliveryPort::saveDelivery)
    }
}
