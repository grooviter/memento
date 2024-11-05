package memento.samples.cargo.command.participant.domain

import memento.samples.cargo.command.participant.domain.events.Registered
import memento.model.Aggregate

import java.time.OffsetDateTime

class Participant extends Aggregate {
    String name
    ParticipantRole role
    OffsetDateTime createdAt

    static Participant register(String name, ParticipantRole role) {
        assert name, "name must be provided"
        assert role, "role must be provided"

        Participant register = new Participant(id: UUID.randomUUID())
        Registered succeeded = Registered.builder()
            .name(name)
            .role(role)
            .build()

        return register.apply(succeeded)
    }

    @Override
    void configure() {
        bind(Registered)
    }
}
