package memento.samples.cargo.command.participant.application.port

import groovy.transform.builder.Builder
import memento.samples.cargo.command.participant.domain.ParticipantRole

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
