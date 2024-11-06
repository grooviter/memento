package memento.guide.aggregate.nobind

import memento.model.Event

class DeliveryRequested extends Event<Delivery> {
    String clientId
    Date deliveryRequestedAt = new Date()
}
