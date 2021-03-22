package io.grooviter.memento.cargo.command.delivery.adapter.rest

import io.grooviter.memento.cargo.command.delivery.adapter.rest.payload.ReceivedPayload
import io.grooviter.memento.cargo.command.delivery.application.port.in.UseCases
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.tags.Tag

import javax.inject.Inject
import javax.validation.Valid

@Tag(name = 'DELIVERY')
@Controller("/delivery")
class DeclareReceivedController {

    @Inject
    UseCases.DeclareAsReceivedCommand declareAsReceivedCommand

    @Post('{deliveryId}/received')
    void declareReceived(@PathVariable UUID deliveryId, @Body @Valid ReceivedPayload payload) {
        UseCases.DeclareAsReceivedCommand.Params params = UseCases.DeclareAsReceivedCommand.Params.builder()
            .deliveryId(deliveryId)
            .receivedBy(payload.clientId)
            .comments(payload.comments)
            .build()

        declareAsReceivedCommand.received(params)
    }
}
