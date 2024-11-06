package memento.guide.aggregate.full

import memento.model.Event

class DeliveryReceived extends Event<Delivery> {
    Date deliveryReceivedAt = new Date()
}
