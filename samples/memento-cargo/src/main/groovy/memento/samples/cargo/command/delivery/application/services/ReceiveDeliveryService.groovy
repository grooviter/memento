package memento.samples.cargo.command.delivery.application.services

import memento.samples.cargo.command.delivery.application.port.in.UseCases
import memento.samples.cargo.command.delivery.application.port.out.DeliveryPorts
import io.micronaut.retry.annotation.Retryable

import jakarta.inject.Inject
import jakarta.inject.Singleton
import io.micronaut.transaction.annotation.Transactional

@Singleton
class ReceiveDeliveryService implements UseCases.DeclareAsReceivedCommand {

    @Inject
    DeliveryPorts.LoadDeliveryPort loadDeliveryPort

    @Inject
    DeliveryPorts.SaveDeliveryPort saveDeliveryPort

    @Override
    @Retryable
    @Transactional
    void received(Params params) {
        loadDeliveryPort.loadById(params.deliveryId)
            .map(delivery -> delivery.claimAsReceived(params.receivedBy, params.comments))
            .ifPresent(saveDeliveryPort::saveDelivery)
    }
}
