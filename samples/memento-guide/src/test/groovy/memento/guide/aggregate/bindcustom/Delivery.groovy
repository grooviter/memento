package memento.guide.aggregate.bindcustom

import groovy.transform.InheritConstructors
import memento.model.Aggregate

@InheritConstructors
class Delivery extends Aggregate implements DeliveryProcess {
    String clientId
    Date deliveryRequestedAt, deliveryReceivedAt

    @Override
    void requested(String clientId) {
        this.apply(new DeliveryRequested(userId: clientId))
    }

    @Override
    void received() {
        this.apply(new DeliveryReceived())
    }

    @Override
    void configure() {
        bind(DeliveryRequested) { Delivery delivery, DeliveryRequested event ->
            delivery.clientId = event.userId
            delivery.deliveryRequestedAt = event.requestedAt
        }

        bind(DeliveryReceived) { Delivery delivery, DeliveryReceived event ->
            delivery.deliveryReceivedAt = event.receivedAt
        }
    }
}
