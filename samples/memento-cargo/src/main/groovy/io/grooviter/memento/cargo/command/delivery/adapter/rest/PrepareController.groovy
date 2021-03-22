package io.grooviter.memento.cargo.command.delivery.adapter.rest

import io.grooviter.memento.cargo.command.delivery.adapter.rest.payload.PreparedPayload
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
class PrepareController {

    @Inject
    UseCases.DeclareAsPreparedCommand declareAsPreparedCommand

    @Post('{id}/prepared')
    void declarePrepared(@PathVariable UUID id, @Body @Valid PreparedPayload payload) {
        UseCases.DeclareAsPreparedCommand.Params params = UseCases.DeclareAsPreparedCommand.Params.builder()
                .deliveryId(id)
                .preparedBy(payload.employeeId)
                .comments(payload.comments)
                .build()

        declareAsPreparedCommand.prepared(params)
    }
}
