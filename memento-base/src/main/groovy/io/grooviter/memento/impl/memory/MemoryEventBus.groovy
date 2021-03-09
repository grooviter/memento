package io.grooviter.memento.impl.memory

import io.grooviter.memento.EventBusPort
import io.grooviter.memento.SerdePort
import io.grooviter.memento.model.Event
import io.grooviter.memento.model.SnapshotEvent
import groovy.util.logging.Slf4j

@Slf4j
class MemoryEventBus implements EventBusPort {

    @Override
    void publish(Event event, SerdePort serdePort) {
        if (!(event instanceof SnapshotEvent)) {
            log.debug("publishing an event store event: ${serdePort.eventToJSON(event)}")
        }
    }

    @Override
    void publishAsync(Event event, SerdePort serdePort) {
        this.publish(event, serdePort)
    }
}
