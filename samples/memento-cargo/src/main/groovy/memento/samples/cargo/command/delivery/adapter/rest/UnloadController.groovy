package memento.samples.cargo.command.delivery.adapter.rest

import memento.samples.cargo.command.delivery.adapter.rest.payload.UnloadedPayload
import memento.samples.cargo.command.delivery.application.port.UseCases
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.tags.Tag

import jakarta.inject.Inject
import jakarta.validation.Valid

@Tag(name = 'DELIVERY')
@Controller("/delivery")
class UnloadController {

    @Inject
    UseCases.UnloadFromTrailerCommand unloadFromTrailerCommand

    @Post('{deliveryId}/unloaded')
    void unload(@PathVariable UUID deliveryId, @Body @Valid UnloadedPayload payload) {
        UseCases.UnloadFromTrailerCommand.Params params = UseCases.UnloadFromTrailerCommand.Params.builder()
            .deliveryId(deliveryId)
            .unloadedBy(payload.driverId)
            .comments(payload.comments)
            .build()

        unloadFromTrailerCommand.unloaded(params)
    }
}
