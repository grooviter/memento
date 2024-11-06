package memento.guide.aggregate.full

import memento.model.Event

class DeliveryRequested extends Event<Delivery> {
    String clientId
    Date deliveryRequestedAt = new Date()
}
