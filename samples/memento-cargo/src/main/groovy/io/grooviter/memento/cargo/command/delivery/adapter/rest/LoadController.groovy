package io.grooviter.memento.cargo.command.delivery.adapter.rest

import io.grooviter.memento.cargo.command.delivery.adapter.rest.payload.LoadedPayload
import io.grooviter.memento.cargo.command.delivery.application.port.in.UseCases
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.tags.Tag

import jakarta.inject.Inject
import jakarta.validation.Valid

@Tag(name = 'DELIVERY')
@Controller("/delivery")
class LoadController {
    @Inject
    UseCases.LoadInTrailerCommand loadInTrailerCommand

    @Post('{id}/loaded')
    void load(@PathVariable UUID id, @Body @Valid LoadedPayload payload) {
        UseCases.LoadInTrailerCommand.Params params = UseCases.LoadInTrailerCommand.Params.builder()
                .deliveryId(id)
                .loadedBy(payload.driverId)
                .comments(payload.comments)
                .build()

        loadInTrailerCommand.loaded(params)
    }
}
