package memento.samples.cargo.command.delivery.adapter.persistence

import memento.EventStore
import memento.samples.cargo.command.common.adapter.eventstore.CommandQualifier
import memento.samples.cargo.command.delivery.application.port.DeliveryPorts
import memento.samples.cargo.command.delivery.domain.Delivery

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class DeliveryPersistenceAdapter implements
    DeliveryPorts.LoadDeliveryPort,
    DeliveryPorts.SaveDeliveryPort {

    @Inject
    @CommandQualifier
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
