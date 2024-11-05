package memento.samples.cargo.query.participant.adapter.eventstore.producer

import com.fasterxml.jackson.databind.ObjectMapper
import memento.samples.cargo.query.participant.adapter.eventstore.events.Created
import memento.mn.jdbc.MicroEventBus
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.context.event.ApplicationEventPublisher

import jakarta.inject.Inject
import jakarta.inject.Singleton

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
