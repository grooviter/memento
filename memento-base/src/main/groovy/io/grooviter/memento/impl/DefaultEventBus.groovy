package io.grooviter.memento.impl

import io.grooviter.memento.EventBusPort
import io.grooviter.memento.SerdePort
import io.grooviter.memento.model.Event

import java.util.function.Consumer

class DefaultEventBus implements EventBusPort {

    private Consumer<Event> eventConsumer

    private DefaultEventBus(Consumer<Event> eventConsumer) {
        this.eventConsumer = eventConsumer
    }

    @Override
    void publish(Event event, SerdePort serdePort) {
        this.eventConsumer.accept(event)
    }

    @Override
    void publishAsync(Event event, SerdePort serdePort) {
        this.eventConsumer.accept(event)
    }

    static EventBusPort create(Consumer<Event> consumer) {
        return new DefaultEventBus(consumer)
    }
}
