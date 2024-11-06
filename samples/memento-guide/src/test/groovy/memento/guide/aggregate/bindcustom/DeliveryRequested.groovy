package memento.guide.aggregate.bindcustom

import memento.model.Event

class DeliveryRequested extends Event<Delivery> {
    String userId
    Date requestedAt = new Date()
}
