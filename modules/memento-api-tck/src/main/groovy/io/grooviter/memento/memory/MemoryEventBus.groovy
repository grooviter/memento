package io.grooviter.memento.memory

import io.grooviter.memento.EventBusPort
import io.grooviter.memento.EventSerdePort
import io.grooviter.memento.model.Event
import io.grooviter.memento.model.SnapshotEvent
import groovy.util.logging.Slf4j

@Slf4j
class MemoryEventBus implements EventBusPort {

    @Override
    void publish(Event event, EventSerdePort serdePort) {
        if (!(event instanceof SnapshotEvent)) {
            MemoryEventBus.log.debug("publishing an event store event: ${serdePort.eventToJSON(event)}")
        }
    }

    @Override
    void publishAsync(Event event, EventSerdePort serdePort) {
        this.publish(event, serdePort)
    }
}
