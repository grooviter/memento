package memento.samples.cargo.query.participant.adapter.eventstore.producer

import groovy.util.logging.Slf4j
import memento.samples.cargo.query.participant.application.port.UseCases
import memento.samples.cargo.query.participant.adapter.eventstore.events.Created
import io.micronaut.runtime.event.annotation.EventListener

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Slf4j
@Singleton
@SuppressWarnings('unused')
class ParticipantCreatedProducer {

    @Inject
    UseCases.SaveNewParticipantCommand saveNewParticipantCommand

    @EventListener
    void onParticipantCreated(Created created) {
        UseCases.SaveNewParticipantCommand.Params params = UseCases.SaveNewParticipantCommand.Params.builder()
            .participantId(created.id)
            .name(created.name)
            .createdAt(created.createdAt)
            .role(created.role)
            .build()

        saveNewParticipantCommand.save(params)
    }
}
