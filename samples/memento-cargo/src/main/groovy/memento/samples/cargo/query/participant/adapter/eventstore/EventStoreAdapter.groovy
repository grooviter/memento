package memento.samples.cargo.query.participant.adapter.eventstore

import memento.EventStore
import memento.samples.cargo.query.common.adapter.eventstore.QueryQualifier
import memento.samples.cargo.query.participant.application.port.ParticipantQueryPorts
import memento.samples.cargo.query.participant.domain.Participant

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
