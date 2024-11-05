package memento.samples.cargo.query.delivery.application.port

import memento.samples.cargo.query.delivery.domain.Delivery

interface DeliveryQueryPorts {

    interface LoadDeliveryById {
        Optional<Delivery> findById(UUID id)
    }

    interface UpdateDeliveryPort {
        void updateDelivery(Delivery delivery)
    }

    interface SaveDeliveryPort {
        void saveDelivery(Delivery delivery)
    }
}