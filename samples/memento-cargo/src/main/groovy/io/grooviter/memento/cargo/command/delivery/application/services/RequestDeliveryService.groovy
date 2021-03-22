package io.grooviter.memento.cargo.command.delivery.application.services

import io.grooviter.memento.cargo.command.delivery.application.port.in.UseCases
import io.grooviter.memento.cargo.command.delivery.application.port.out.DeliveryPorts
import io.grooviter.memento.cargo.command.delivery.domain.Delivery

import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional

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
