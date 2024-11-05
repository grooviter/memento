package memento.samples.cargo.command.delivery.adapter.rest.payload

import io.micronaut.core.annotation.Introspected

import jakarta.validation.constraints.NotNull

@Introspected
class PreparedPayload {
    @NotNull
    UUID employeeId
    String comments
}
