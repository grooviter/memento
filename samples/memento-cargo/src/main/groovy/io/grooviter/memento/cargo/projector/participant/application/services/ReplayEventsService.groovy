package io.grooviter.memento.cargo.projector.participant.application.services

import groovy.util.logging.Slf4j
import io.grooviter.memento.cargo.projector.participant.application.port.in.UseCases
import io.grooviter.memento.cargo.projector.participant.application.port.out.ParticipantProjectorPorts

import javax.inject.Inject
import javax.inject.Singleton

@Slf4j
@Singleton
class ReplayEventsService implements UseCases.ReplayEventsCommand {

    @Inject
    ParticipantProjectorPorts.ParticipantCountPort participantCountPort

    @Inject
    ParticipantProjectorPorts.ListParticipantsFromEventsPort listParticipantsFromEventsPort

    @Inject
    ParticipantProjectorPorts.SaveParticipantPort saveParticipantPort

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
