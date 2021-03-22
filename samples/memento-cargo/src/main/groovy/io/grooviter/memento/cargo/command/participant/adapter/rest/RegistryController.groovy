package io.grooviter.memento.cargo.command.participant.adapter.rest

import io.grooviter.memento.cargo.command.participant.adapter.rest.payload.NewParticipantPayload
import io.grooviter.memento.cargo.command.participant.application.port.in.UseCases
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.tags.Tag

import javax.inject.Inject
import javax.validation.Valid

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
