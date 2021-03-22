package io.grooviter.memento.cargo.projector.delivery.adapter.persistence

import io.grooviter.memento.cargo.projector.delivery.adapter.persistence.repository.DeliveryRepository
import io.grooviter.memento.cargo.projector.delivery.application.port.out.DeliveryProjectorPorts
import io.grooviter.memento.cargo.projector.delivery.domain.Delivery

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeliveryPersistenceAdapter implements
    DeliveryProjectorPorts.LoadDeliveryPort,
    DeliveryProjectorPorts.SaveDeliveryPort,
    DeliveryProjectorPorts.UpdateDeliveryPort {

    @Inject
    DeliveryRepository deliveryRepository

    @Override
    Optional<Delivery> loadDelivery(UUID id) {
        return deliveryRepository.findById(id).map(Mappers::toDomain)
    }

    @Override
    void saveDelivery(Delivery delivery) {
        deliveryRepository.save(Mappers.toEntity(delivery))
    }

    @Override
    void updateDelivery(Delivery delivery) {
        deliveryRepository.update(Mappers.toEntity(delivery))
    }
}
