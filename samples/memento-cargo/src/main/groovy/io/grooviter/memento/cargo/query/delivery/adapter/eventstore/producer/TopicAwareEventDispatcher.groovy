package io.grooviter.memento.cargo.query.delivery.adapter.eventstore.producer

import com.fasterxml.jackson.databind.ObjectMapper
import io.grooviter.memento.cargo.query.delivery.adapter.eventstore.events.Created
import io.grooviter.memento.cargo.query.delivery.adapter.eventstore.events.Delivered
import io.grooviter.memento.cargo.query.delivery.adapter.eventstore.events.OnRoute
import io.grooviter.memento.cargo.shared.TopicAwareEvent
import io.micronaut.context.event.ApplicationEventPublisher
import io.micronaut.runtime.event.annotation.EventListener

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@SuppressWarnings("unused")
class TopicAwareEventDispatcher {

    @Inject
    ApplicationEventPublisher applicationEventPublisher

    @Inject
    ObjectMapper objectMapper

    @EventListener
    void onTopicAwareEventListener(TopicAwareEvent event) {
        switch(event.topic) {
            case 'DELIVERY_REQUESTED':                     publishCreated(event)  ; break
            case 'DELIVERY_RECEIVED':                      publishReceived(event) ; break
            case ['DELIVERY_LOADED', 'DELIVERY_UNLOADED']: publishOnRoute(event)  ; break
        }
    }

    private void publishCreated(TopicAwareEvent event) {
        Created created = objectMapper
            .readerFor(Created)
            .readValue(event.json)

        applicationEventPublisher.publishEvent(created)
    }

    private void publishReceived(TopicAwareEvent event) {
        Delivered received = objectMapper
            .readerFor(Delivered)
            .readValue(event.json)

        applicationEventPublisher.publishEvent(received)
    }

    private void publishOnRoute(TopicAwareEvent event) {
        OnRoute onRoute = objectMapper
            .readerFor(OnRoute)
            .readValue(event.json)

        applicationEventPublisher.publishEvent(onRoute)
    }
}
