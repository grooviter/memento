package io.grooviter.memento.bookkeeper.infra.eventstore

import io.grooviter.memento.EventBusPort
import io.grooviter.memento.SerdePort
import io.grooviter.memento.model.Event
import io.micronaut.context.event.ApplicationEventPublisher

import javax.inject.Singleton

@Singleton
class MicronautEventBus implements EventBusPort {

    ApplicationEventPublisher applicationEventPublisher

    @Override
    void publish(Event event, SerdePort serdePort) {

    }

    @Override
    void publishAsync(Event event, SerdePort serdePort) {

    }
}
