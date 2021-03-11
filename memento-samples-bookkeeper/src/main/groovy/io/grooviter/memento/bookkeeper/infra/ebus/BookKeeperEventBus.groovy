package io.grooviter.memento.bookkeeper.infra.ebus

import com.google.common.eventbus.EventBus
import groovy.transform.TupleConstructor
import io.grooviter.memento.EventBusPort
import io.grooviter.memento.SerdePort
import io.grooviter.memento.model.Event

@TupleConstructor
class BookKeeperEventBus implements EventBusPort {
    EventBus eventBus

    @Override
    void publish(Event event, SerdePort serdePort) {
        this.eventBus.post(event)
    }

    @Override
    void publishAsync(Event event, SerdePort serdePort) {
        throw new RuntimeException("Async publishing not available")
    }
}
