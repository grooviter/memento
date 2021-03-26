package io.grooviter.memento.cargo.command.delivery.adapter.ebus

import io.grooviter.memento.EventBusPort
import io.grooviter.memento.EventSerdePort
import io.grooviter.memento.cargo.shared.TopicAwareEvent
import io.grooviter.memento.model.Event
import io.micronaut.context.annotation.Replaces
import io.micronaut.context.event.ApplicationEventPublisher

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Replaces(bean = EventBusPort)
class TopicAwareEventBus implements EventBusPort {

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

    private TopicAwareEvent getTopicAwareEvent(Event event, EventSerdePort serdePort) {
        String topic = serdePort.resolveAlias(event.getClass())
        String json  = serdePort.eventToJSON(event)
        return new TopicAwareEvent(topic, json)
    }
}
