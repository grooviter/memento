package io.grooviter.memento.cargo.command.participant.adapter.rest.payload

import io.grooviter.memento.cargo.command.participant.domain.ParticipantRole
import io.micronaut.core.annotation.Introspected

import jakarta.validation.constraints.NotNull

@Introspected
class NewParticipantPayload {
    @NotNull
    String name

    @NotNull
    ParticipantRole role
}
