package io.grooviter.memento.cargo.command.delivery.adapter.persistence

import io.grooviter.memento.EventStore
import io.grooviter.memento.cargo.command.delivery.application.port.out.DeliveryPorts
import io.grooviter.memento.cargo.command.delivery.domain.Delivery

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeliveryPersistenceAdapter implements
    DeliveryPorts.LoadDeliveryPort,
    DeliveryPorts.SaveDeliveryPort {

    @Inject
    EventStore eventStore

    @Override
    void saveDelivery(Delivery delivery) {
        this.eventStore.save(delivery)
    }

    @Override
    Optional<Delivery> loadById(UUID id) {
        return this.eventStore.load(id, Delivery)
    }
}
