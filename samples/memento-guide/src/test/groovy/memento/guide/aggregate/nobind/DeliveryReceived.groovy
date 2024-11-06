package memento.guide.aggregate.nobind

import memento.model.Event

class DeliveryReceived extends Event<Delivery> {
    Date deliveryReceivedAt = new Date()
}
