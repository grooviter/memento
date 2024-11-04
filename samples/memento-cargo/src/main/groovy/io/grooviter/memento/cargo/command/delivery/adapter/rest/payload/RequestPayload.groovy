package io.grooviter.memento.cargo.command.delivery.adapter.rest.payload

import io.micronaut.core.annotation.Introspected

import jakarta.validation.constraints.NotNull

@Introspected
class RequestPayload {
    @NotNull
    UUID clientId
}
