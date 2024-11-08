package memento.samples.cargo.query.participant.adapter.rest

import memento.samples.cargo.query.participant.application.port.UseCases
import memento.samples.cargo.query.participant.domain.Participant
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.swagger.v3.oas.annotations.tags.Tag

import jakarta.inject.Inject

@Tag(name = 'PARTICIPANT')
@Controller('/participant')
class ParticipantQueryController {

    @Inject
    UseCases.ShowPaginatedParticipantsQuery showPaginatedParticipantsQuery

    @Get
    List<Participant> list() {
        return showPaginatedParticipantsQuery.list()
    }
}
