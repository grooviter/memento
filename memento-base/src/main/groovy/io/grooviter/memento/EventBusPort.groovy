package io.grooviter.memento

import io.grooviter.memento.model.Event

interface EventBusPort {

    void publish(Event event, SerdePort serdePort)
    void publishAsync(Event event, SerdePort serdePort)
}