package io.grooviter.memento.cargo.query.participant.application.services

import groovy.util.logging.Slf4j
import io.grooviter.memento.cargo.query.participant.application.port.in.UseCases
import io.grooviter.memento.cargo.query.participant.application.port.out.ParticipantQueryPorts

import javax.inject.Inject
import javax.inject.Singleton

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
    void replay() {
        if (!participantCountPort.count()) {
            log.debug("no participants present replaying...")
            listParticipantsFromEventsPort
                .listParticipants()
                .forEach(saveParticipantPort::save)
        }
    }
}
