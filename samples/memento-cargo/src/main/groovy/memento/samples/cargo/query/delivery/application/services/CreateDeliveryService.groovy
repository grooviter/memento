package memento.samples.cargo.query.delivery.application.services

import groovy.util.logging.Slf4j
import memento.samples.cargo.query.delivery.application.port.in.UseCases
import memento.samples.cargo.query.delivery.application.port.out.DeliveryQueryPorts
import memento.samples.cargo.query.delivery.domain.Delivery

import jakarta.inject.Inject
import jakarta.inject.Singleton
import io.micronaut.transaction.annotation.Transactional

@Slf4j
@Singleton
class CreateDeliveryService implements UseCases.CreateDeliveryCommand {

    @Inject
    DeliveryQueryPorts.LoadDeliveryById loadDeliveryById

    @Inject
    DeliveryQueryPorts.SaveDeliveryPort saveDeliveryPort

    @Override
    @Transactional
    void create(Params params) {
        Optional<Delivery> delivery = loadDeliveryById.findById(params.deliveryId)
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
