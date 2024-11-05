package memento.samples.cargo.command.delivery.application.port
import memento.samples.cargo.command.delivery.domain.Delivery
interface DeliveryPorts {

    interface LoadDeliveryPort {
        Optional<Delivery> loadById(UUID id)
    }

    interface SaveDeliveryPort {
        void saveDelivery(Delivery delivery)
    }
}