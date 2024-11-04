package io.grooviter.memento.cargo.query.delivery.adapter.persistence

import io.grooviter.memento.cargo.query.delivery.adapter.persistence.repository.DeliveryEntityRepository
import io.grooviter.memento.cargo.query.delivery.application.port.out.DeliveryQueryPorts
import io.grooviter.memento.cargo.query.delivery.domain.Delivery

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class DeliveryPersistenceAdapter implements
    DeliveryQueryPorts.LoadDeliveryById,
    DeliveryQueryPorts.SaveDeliveryPort,
    DeliveryQueryPorts.UpdateDeliveryPort {

    @Inject
    DeliveryEntityRepository deliveryEntityRepository

    @Override
    Optional<Delivery> findById(UUID id) {
        return deliveryEntityRepository.findById(id).map(Mappers::toDomain)
    }

    @Override
    void saveDelivery(Delivery delivery) {
        deliveryEntityRepository.save(Mappers.toEntity(delivery))
    }

    @Override
    void updateDelivery(Delivery delivery) {
        deliveryEntityRepository.update(Mappers.toEntity(delivery))
    }
}
