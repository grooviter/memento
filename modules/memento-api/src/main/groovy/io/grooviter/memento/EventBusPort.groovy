package io.grooviter.memento

import io.grooviter.memento.model.Event

interface EventBusPort {
    void publish(Event event, EventSerdePort serdePort)
    void publishAsync(Event event, EventSerdePort serdePort)
}