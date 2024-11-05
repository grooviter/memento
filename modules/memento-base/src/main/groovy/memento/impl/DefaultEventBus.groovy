package memento.impl

import memento.EventBusPort
import memento.EventSerdePort
import memento.model.Event

import java.util.function.Consumer

class DefaultEventBus implements EventBusPort {

    private Consumer<Event> eventConsumer

    private DefaultEventBus(Consumer<Event> eventConsumer) {
        this.eventConsumer = eventConsumer
    }

    @Override
    void publish(Event event, EventSerdePort serdePort) {
        this.eventConsumer.accept(event)
    }

    @Override
    void publishAsync(Event event, EventSerdePort serdePort) {
        this.eventConsumer.accept(event)
    }

    static EventBusPort create(Consumer<Event> consumer) {
        return new DefaultEventBus(consumer)
    }
}
