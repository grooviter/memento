package memento.samples.cargo.query.delivery.adapter.eventstore.producer

import com.fasterxml.jackson.databind.ObjectMapper
import memento.samples.cargo.query.delivery.adapter.eventstore.events.Created
import memento.samples.cargo.query.delivery.adapter.eventstore.events.Delivered
import memento.samples.cargo.query.delivery.adapter.eventstore.events.OnRoute
import memento.mn.jdbc.MicroEventBus
import io.micronaut.context.event.ApplicationEventPublisher
import io.micronaut.runtime.event.annotation.EventListener

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
@SuppressWarnings("unused")
class TopicAwareEventDispatcher {

    @Inject
    ApplicationEventPublisher applicationEventPublisher

    @Inject
    ObjectMapper objectMapper

    @EventListener
    void onTopicAwareEventListener(MicroEventBus.TopicAwareEvent event) {
        switch(event.topic) {
            case 'DELIVERY_REQUESTED':                     publishCreated(event)  ; break
            case 'DELIVERY_RECEIVED':                      publishReceived(event) ; break
            case ['DELIVERY_LOADED', 'DELIVERY_UNLOADED']: publishOnRoute(event)  ; break
        }
    }

    private void publishCreated(MicroEventBus.TopicAwareEvent event) {
        Created created = objectMapper
            .readerFor(Created)
            .readValue(event.json)

        applicationEventPublisher.publishEvent(created)
    }

    private void publishReceived(MicroEventBus.TopicAwareEvent event) {
        Delivered received = objectMapper
            .readerFor(Delivered)
            .readValue(event.json)

        applicationEventPublisher.publishEvent(received)
    }

    private void publishOnRoute(MicroEventBus.TopicAwareEvent event) {
        OnRoute onRoute = objectMapper
            .readerFor(OnRoute)
            .readValue(event.json)

        applicationEventPublisher.publishEvent(onRoute)
    }
}
