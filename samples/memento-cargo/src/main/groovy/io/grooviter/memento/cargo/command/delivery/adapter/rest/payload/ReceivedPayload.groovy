package io.grooviter.memento.cargo.command.delivery.adapter.rest.payload

import io.micronaut.core.annotation.Introspected

import javax.validation.constraints.NotNull

@Introspected
class ReceivedPayload {
    @NotNull
    UUID clientId
    String comments
}
