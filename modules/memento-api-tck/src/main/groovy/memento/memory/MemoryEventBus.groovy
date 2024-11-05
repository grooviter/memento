package memento.memory

import memento.EventBusPort
import memento.EventSerdePort
import memento.model.Event
import memento.model.SnapshotEvent
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
