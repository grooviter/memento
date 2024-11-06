package memento.guide.aggregate.nobind

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

    private void apply(DeliveryRequested requested) {
        super.apply(requested) // <1>
        this.clientId = requested.clientId // <2>
        this.deliveryRequestedAt = requested.deliveryRequestedAt
    }

    private void apply(DeliveryReceived received) {
        super.apply(received)
        this.deliveryReceivedAt = received.deliveryReceivedAt
    }
}
