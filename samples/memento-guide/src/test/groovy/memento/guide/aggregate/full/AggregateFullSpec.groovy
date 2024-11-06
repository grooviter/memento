package memento.guide.aggregate.full

import spock.lang.Specification

class AggregateFullSpec extends Specification {
    // tag::adding_events[]
    void 'creating events'() {
        setup:
        Delivery delivery = new Delivery(UUID.randomUUID())

        when:
        delivery.requested("1000")

        then:
        delivery.eventList.size() == 1
        delivery.eventList[0].version == 1

        when:
        delivery.received()

        then:
        delivery.eventList.size() == 2
        delivery.eventList[1].version == 2
    }
    // end::adding_events[]
}
