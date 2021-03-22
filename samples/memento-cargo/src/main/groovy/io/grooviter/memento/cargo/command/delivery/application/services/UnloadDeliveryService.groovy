package io.grooviter.memento.cargo.command.delivery.application.services

import io.grooviter.memento.cargo.command.delivery.application.port.in.UseCases
import io.grooviter.memento.cargo.command.delivery.application.port.out.DeliveryPorts
import io.micronaut.retry.annotation.Retryable

import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional

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
