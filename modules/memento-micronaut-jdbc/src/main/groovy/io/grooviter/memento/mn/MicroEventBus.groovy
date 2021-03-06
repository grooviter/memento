package io.grooviter.memento.mn

import io.grooviter.memento.EventBusPort
import io.grooviter.memento.EventSerdePort
import io.grooviter.memento.model.Event
import io.micronaut.context.event.ApplicationEventPublisher

import javax.inject.Inject
import javax.inject.Singleton
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
