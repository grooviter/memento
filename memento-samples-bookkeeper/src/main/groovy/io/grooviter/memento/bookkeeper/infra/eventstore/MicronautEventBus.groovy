package io.grooviter.memento.bookkeeper.infra.eventstore

import io.grooviter.memento.EventBusPort
import io.grooviter.memento.SerdePort
import io.grooviter.memento.model.Event
import io.micronaut.context.event.ApplicationEventPublisher

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MicronautEventBus implements EventBusPort {

    @Inject
    ApplicationEventPublisher applicationEventPublisher

    @Override
    void publish(Event event, SerdePort serdePort) {
        String topic = serdePort.resolveAlias(event.getClass())
        String json  = serdePort.eventToJSON(event)

        applicationEventPublisher.publishEvent(new TopicAwareEvent(topic, json))
    }

    @Override
    void publishAsync(Event event, SerdePort serdePort) {
        String topic = serdePort.resolveAlias(event.getClass())
        String json  = serdePort.eventToJSON(event)

        applicationEventPublisher.publishEventAsync(new TopicAwareEvent(topic, json))
    }
}
