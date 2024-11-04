package io.grooviter.memento.cargo.query.participant.adapter.eventstore

import io.grooviter.memento.EventStore
import io.grooviter.memento.cargo.query.common.adapter.eventstore.QueryQualifier
import io.grooviter.memento.cargo.query.participant.application.port.out.ParticipantQueryPorts
import io.grooviter.memento.cargo.query.participant.domain.Participant

import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.stream.Stream

@Singleton
class EventStoreAdapter implements ParticipantQueryPorts.ListParticipantsFromEventsPort {

    private static final String[] EVENTS = ['PARTICIPANT_REGISTERED']

    @Inject
    @QueryQualifier
    EventStore eventStore

    @Override
    Stream<Participant> listParticipants() {
        return eventStore.listEvents(EVENTS).map(Mappers::toParticipant)
    }
}
