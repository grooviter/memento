package memento.mn.jdbc

import memento.EventBusPort
import memento.EventSerdePort
import memento.model.Event
import io.micronaut.context.event.ApplicationEventPublisher

import jakarta.inject.Inject
import jakarta.inject.Singleton
import groovy.transform.TupleConstructor

@Singleton
class MicroEventBus implements EventBusPort {

    @TupleConstructor
    static class TopicAwareEvent {
        String topic
        String json
    }

    @Inject
    ApplicationEventPublisher applicationEventPublisher

    @Override
    void publish(Event event, EventSerdePort serdePort) {
        applicationEventPublisher.publishEvent(getTopicAwareEvent(event, serdePort))
    }

    @Override
    void publishAsync(Event event, EventSerdePort serdePort) {
        applicationEventPublisher.publishEventAsync(getTopicAwareEvent(event, serdePort))
    }

    private static TopicAwareEvent getTopicAwareEvent(Event event, EventSerdePort serdePort) {
        String topic = serdePort.resolveAlias(event.getClass())
        String json  = serdePort.eventToJSON(event)
        return new TopicAwareEvent(topic, json)
    }
}
