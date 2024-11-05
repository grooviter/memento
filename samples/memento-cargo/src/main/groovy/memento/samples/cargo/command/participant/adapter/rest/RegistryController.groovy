package memento.samples.cargo.command.participant.adapter.rest

import memento.samples.cargo.command.participant.adapter.rest.payload.NewParticipantPayload
import memento.samples.cargo.command.participant.application.port.UseCases
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.tags.Tag

import jakarta.inject.Inject
import jakarta.validation.Valid

@Tag(name = 'PARTICIPANT')
@Controller('/registry')
class RegistryController {

    @Inject
    UseCases.RegisterParticipantCommand registerParticipantCommand

    @Post
    UUID registry(@Body @Valid NewParticipantPayload payload) {
        UseCases.RegisterParticipantCommand.Params params = UseCases.RegisterParticipantCommand.Params.builder()
            .name(payload.name)
            .role(payload.role)
            .build()

        return registerParticipantCommand.registerParticipant(params)
    }
}
