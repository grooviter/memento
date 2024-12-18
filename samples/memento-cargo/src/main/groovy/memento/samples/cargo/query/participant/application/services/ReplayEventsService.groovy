package memento.samples.cargo.query.participant.application.services

import groovy.util.logging.Slf4j
import memento.samples.cargo.query.participant.application.port.UseCases
import memento.samples.cargo.query.participant.application.port.ParticipantQueryPorts

import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional

@Slf4j
@Singleton
class ReplayEventsService implements UseCases.ReplayEventsCommand {

    @Inject
    ParticipantQueryPorts.ParticipantCountPort participantCountPort

    @Inject
    ParticipantQueryPorts.ListParticipantsFromEventsPort listParticipantsFromEventsPort

    @Inject
    ParticipantQueryPorts.SaveParticipantPort saveParticipantPort

    @Override
    @Transactional
    void replay() {
        if (!participantCountPort.count()) {
            log.debug("no participants present replaying...")
            listParticipantsFromEventsPort
                .listParticipants()
                .forEach(saveParticipantPort::save)
        }
    }
}
