package io.grooviter.memento.cargo.query.participant.adapter.eventstore.producer

import com.fasterxml.jackson.databind.ObjectMapper
import io.grooviter.memento.cargo.query.participant.adapter.eventstore.events.Created
import io.grooviter.memento.mn.MicroEventBus
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.context.event.ApplicationEventPublisher

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopicAwareEventDispatcher implements ApplicationEventListener<MicroEventBus.TopicAwareEvent> {

    @Inject
    ApplicationEventPublisher applicationEventPublisher

    @Inject
    ObjectMapper objectMapper

    @Override
    void onApplicationEvent(MicroEventBus.TopicAwareEvent event) {
        switch (event.topic) {
            case 'PARTICIPANT_REGISTERED': dispatchParticipantRegistered(event); break
        }
    }

    private dispatchParticipantRegistered(MicroEventBus.TopicAwareEvent event) {
        Created created = objectMapper
            .readerFor(Created)
            .readValue(event.json)

        applicationEventPublisher.publishEvent(created)
    }
}
