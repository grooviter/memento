package memento

import memento.model.Event

interface EventBusPort {
    void publish(Event event, EventSerdePort serdePort)
    void publishAsync(Event event, EventSerdePort serdePort)
}