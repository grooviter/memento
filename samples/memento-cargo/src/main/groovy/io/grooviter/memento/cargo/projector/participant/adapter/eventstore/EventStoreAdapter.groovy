package io.grooviter.memento.cargo.projector.participant.adapter.eventstore

import io.grooviter.memento.EventStore
import io.grooviter.memento.cargo.projector.common.adapter.eventstore.Projector
import io.grooviter.memento.cargo.projector.participant.application.port.out.ParticipantProjectorPorts
import io.grooviter.memento.cargo.projector.participant.domain.Participant

import javax.inject.Inject
import javax.inject.Singleton
import java.util.stream.Stream

@Singleton
class EventStoreAdapter implements ParticipantProjectorPorts.ListParticipantsFromEventsPort {

    private static final String[] EVENTS = ['PARTICIPANT_REGISTERED']

    @Inject
    @Projector
    EventStore eventStore

    @Override
    Stream<Participant> listParticipants() {
        return eventStore.listEvents(EVENTS).map(Mappers::toParticipant)
    }
}
