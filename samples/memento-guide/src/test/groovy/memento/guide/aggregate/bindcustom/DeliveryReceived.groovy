package memento.guide.aggregate.bindcustom

import memento.model.Event

class DeliveryReceived extends Event<Delivery> {
    Date receivedAt = new Date()
}
