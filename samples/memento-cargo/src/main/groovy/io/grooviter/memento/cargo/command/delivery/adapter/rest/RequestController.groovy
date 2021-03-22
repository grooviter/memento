package io.grooviter.memento.cargo.command.delivery.adapter.rest

import io.grooviter.memento.cargo.command.delivery.adapter.rest.payload.RequestPayload
import io.grooviter.memento.cargo.command.delivery.application.port.in.UseCases
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.tags.Tag

import javax.inject.Inject
import javax.validation.Valid

@Tag(name = 'DELIVERY')
@Controller(value = "/delivery", produces = MediaType.APPLICATION_JSON)
class RequestController {

    @Inject
    UseCases.RequestNewDeliveryCommand requestNewDeliveryCommand

    @Post
    UUID request(@Body @Valid RequestPayload requestPayload) {
        UseCases.RequestNewDeliveryCommand.Params params = UseCases.RequestNewDeliveryCommand.Params.builder()
                .userId(requestPayload.clientId)
                .build()

        return requestNewDeliveryCommand.requested(params)
    }
}
