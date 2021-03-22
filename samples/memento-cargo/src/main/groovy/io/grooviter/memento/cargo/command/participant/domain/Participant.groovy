package io.grooviter.memento.cargo.command.participant.domain

import io.grooviter.memento.cargo.command.participant.domain.events.Registered
import io.grooviter.memento.model.Aggregate

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
            .version(register.nextVersion)
            .build()

        return register.apply(succeeded)
    }

    Participant apply(Registered event) {
        super.apply(event)
        this.name = event.name
        this.role = event.role
        this.createdAt = event.createdAt
        return this
    }
}
