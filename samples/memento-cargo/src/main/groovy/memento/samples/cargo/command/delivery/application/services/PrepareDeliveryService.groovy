package memento.samples.cargo.command.delivery.application.services

import memento.samples.cargo.command.delivery.application.port.UseCases
import memento.samples.cargo.command.delivery.application.port.DeliveryPorts
import io.micronaut.retry.annotation.Retryable

import jakarta.inject.Inject
import jakarta.inject.Singleton
import io.micronaut.transaction.annotation.Transactional

@Singleton
class PrepareDeliveryService implements UseCases.DeclareAsPreparedCommand {

    @Inject
    DeliveryPorts.LoadDeliveryPort loadDeliveryPort

    @Inject
    DeliveryPorts.SaveDeliveryPort saveDeliveryPort

    @Override
    @Retryable
    @Transactional
    void prepared(Params params) {
        loadDeliveryPort
            .loadById(params.deliveryId)
            .map(delivery -> delivery.prepare(params.preparedBy))
            .ifPresent(saveDeliveryPort::saveDelivery)
    }
}
