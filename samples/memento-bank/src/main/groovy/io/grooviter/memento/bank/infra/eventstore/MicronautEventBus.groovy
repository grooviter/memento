package io.grooviter.memento.bank.infra.eventstore

import io.grooviter.memento.EventBusPort
import io.grooviter.memento.EventSerdePort
import io.grooviter.memento.model.Event
import io.micronaut.context.event.ApplicationEventPublisher

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MicronautEventBus implements EventBusPort {

    @Inject
    ApplicationEventPublisher applicationEventPublisher

    @Override
    void publish(Event event, EventSerdePort serdePort) {
        String topic = serdePort.resolveAlias(event.getClass())
        String json  = serdePort.eventToJSON(event)

        applicationEventPublisher.publishEvent(new TopicAwareEvent(topic, json))
    }

    @Override
    void publishAsync(Event event, EventSerdePort serdePort) {
        String topic = serdePort.resolveAlias(event.getClass())
        String json  = serdePort.eventToJSON(event)

        applicationEventPublisher.publishEventAsync(new TopicAwareEvent(topic, json))
    }
}
