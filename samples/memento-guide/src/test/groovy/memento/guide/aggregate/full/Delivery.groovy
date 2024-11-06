package memento.guide.aggregate.full

import groovy.transform.InheritConstructors
import memento.model.Aggregate

@InheritConstructors
class Delivery extends Aggregate implements DeliveryProcess {
    String clientId
    Date deliveryRequestedAt, deliveryReceivedAt

    @Override
    void requested(String clientId) {
        this.apply(new DeliveryRequested(clientId: clientId))
    }

    @Override
    void received() {
        this.apply(new DeliveryReceived())
    }

    @Override
    void configure() { // <1>
        bind(DeliveryRequested, DeliveryReceived) // <2>
    }
}
