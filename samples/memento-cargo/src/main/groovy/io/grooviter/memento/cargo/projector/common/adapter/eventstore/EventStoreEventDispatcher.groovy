package io.grooviter.memento.cargo.projector.common.adapter.eventstore

import io.grooviter.memento.EventSerdePort
import io.grooviter.memento.cargo.shared.TopicAwareEvent
import io.grooviter.memento.model.Event
import io.micronaut.context.event.ApplicationEventPublisher
import io.micronaut.runtime.event.annotation.EventListener

import javax.inject.Inject
import javax.inject.Singleton

/**
 * This could be any type of integration with an specific event bus. Here
 * we're using Micronaut {@link EventListener} mechanism, but imagine for
 * instance this would be connected to RabbitMQ and we get a raw json
 * from a queue. We could still use the queue name as alias and the raw
 * json to deserialize using the configured {@link EventSerdePort}
 *
 * @since 0.1.0
 */
@Singleton
@SuppressWarnings('unused')
class EventStoreEventDispatcher {

    @Inject
    ApplicationEventPublisher applicationEventPublisher

    @Inject
    @Projector
    EventSerdePort eventSerdePort

    @EventListener
    void onTopicAwareEventListener(TopicAwareEvent event) {
        Event mementoEvent = eventSerdePort.eventFromJSON(event.topic, event.json)
        applicationEventPublisher.publishEvent(mementoEvent)
    }
}
