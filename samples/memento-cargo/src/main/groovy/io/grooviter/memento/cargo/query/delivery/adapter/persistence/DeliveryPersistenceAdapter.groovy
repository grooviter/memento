package io.grooviter.memento.cargo.query.delivery.adapter.persistence

import io.grooviter.memento.cargo.query.delivery.adapter.persistence.repository.DeliveryEntityRepository
import io.grooviter.memento.cargo.query.delivery.application.port.out.DeliveryQueryPorts
import io.grooviter.memento.cargo.query.delivery.domain.Delivery

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeliveryPersistenceAdapter implements DeliveryQueryPorts.LoadDeliveryById {

    @Inject
    DeliveryEntityRepository deliveryEntityRepository

    @Override
    Optional<Delivery> findById(UUID id) {
        return deliveryEntityRepository.findById(id).map(Mappers::toDomain)
    }
}
