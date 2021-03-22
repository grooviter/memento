package io.grooviter.memento.cargo.command.participant.application.port.in

import groovy.transform.builder.Builder
import io.grooviter.memento.cargo.command.participant.domain.ParticipantRole

class UseCases {

    interface RegisterParticipantCommand {
        @Builder
        class Params {
            String name
            ParticipantRole role
        }

        UUID registerParticipant(Params params)
    }
}
