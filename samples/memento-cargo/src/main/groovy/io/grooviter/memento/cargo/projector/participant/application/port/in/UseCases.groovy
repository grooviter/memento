package io.grooviter.memento.cargo.projector.participant.application.port.in

import groovy.transform.builder.Builder

import java.time.OffsetDateTime

class UseCases {

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
