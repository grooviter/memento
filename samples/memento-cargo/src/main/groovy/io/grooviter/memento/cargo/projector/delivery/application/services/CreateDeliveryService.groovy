package io.grooviter.memento.cargo.projector.delivery.application.services

import groovy.util.logging.Slf4j
import io.grooviter.memento.cargo.projector.delivery.application.port.in.UseCases
import io.grooviter.memento.cargo.projector.delivery.application.port.out.DeliveryProjectorPorts
import io.grooviter.memento.cargo.projector.delivery.domain.Delivery

import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional

@Slf4j
@Singleton
class CreateDeliveryService implements UseCases.CreateDeliveryCommand {

    @Inject
    DeliveryProjectorPorts.LoadDeliveryPort loadDeliveryPort

    @Inject
    DeliveryProjectorPorts.SaveDeliveryPort saveDeliveryPort

    @Override
    @Transactional
    void create(Params params) {
        Optional<Delivery> delivery = loadDeliveryPort.loadDelivery(params.deliveryId)
        delivery.ifPresent(this::logDeliveryIsAlreadyPresent)

        if (delivery.isEmpty()) {
            Delivery newDelivery = new Delivery(
                id: params.deliveryId,
                requestedAt: params.requestedAt,
                status: 'CREATED'
            )
            saveDeliveryPort.saveDelivery(newDelivery)
        }
    }

    void logDeliveryIsAlreadyPresent(Delivery delivery) {
        log.debug("delivery ${delivery.id} is already present")
    }
}
