package io.grooviter.memento.cargo.query.participant.application.port.in

import groovy.transform.builder.Builder
import io.grooviter.memento.cargo.query.participant.domain.Participant

import java.time.OffsetDateTime

class UseCases {

    interface ShowPaginatedParticipantsQuery {
        List<Participant> list()
    }

    interface SaveNewParticipantCommand {
        @Builder
        class Params {
            UUID participantId
            String name, role
            OffsetDateTime createdAt
        }
        void save(Params params)
    }

    interface ReplayEventsCommand {
        void replay()
    }
}
