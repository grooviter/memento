package memento.samples.cargo.command.delivery.application.services

import memento.samples.cargo.command.delivery.application.port.in.UseCases
import memento.samples.cargo.command.delivery.application.port.out.DeliveryPorts
import memento.samples.cargo.command.delivery.domain.Delivery

import jakarta.inject.Inject
import jakarta.inject.Singleton
import io.micronaut.transaction.annotation.Transactional

@Singleton
class RequestDeliveryService implements UseCases.RequestNewDeliveryCommand {

    @Inject
    DeliveryPorts.SaveDeliveryPort saveDeliveryPort

    @Override
    @Transactional
    UUID requested(Params params) {
        Delivery delivery = Delivery.beginDeliveryProcess(params.userId)
        saveDeliveryPort.saveDelivery(delivery)

        return delivery.id
    }
}
