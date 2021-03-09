package io.grooviter.memento.mn

import io.grooviter.memento.EventBusPort
import io.grooviter.memento.SerdePort
import io.grooviter.memento.model.Event
import io.micronaut.context.event.ApplicationEventPublisher

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MicroEventBus implements EventBusPort {

    @Inject
    ApplicationEventPublisher applicationEventPublisher

    @Override
    void publish(Event event, SerdePort serdePort) {
        this.applicationEventPublisher.publishEvent(event)
    }

    @Override
    void publishAsync(Event event, SerdePort serdePort) {
        this.applicationEventPublisher.publishEventAsync(event)
    }
}
