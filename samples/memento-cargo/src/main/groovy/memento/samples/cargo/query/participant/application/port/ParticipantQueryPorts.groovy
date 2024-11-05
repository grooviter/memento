package memento.samples.cargo.query.participant.application.port

import memento.samples.cargo.query.participant.domain.Participant

import java.util.stream.Stream

interface ParticipantQueryPorts {

    interface ListParticipantsPort {
        List<Participant> list()
    }

    interface ParticipantCountPort {
        int count()
    }

    interface SaveParticipantPort {
        void save(Participant participant)
    }

    interface ListParticipantsFromEventsPort {
        Stream<Participant> listParticipants()
    }
}