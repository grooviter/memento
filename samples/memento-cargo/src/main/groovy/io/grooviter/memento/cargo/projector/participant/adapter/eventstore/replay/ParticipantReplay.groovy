package io.grooviter.memento.cargo.projector.participant.adapter.eventstore.replay

import groovy.util.logging.Slf4j
import io.grooviter.memento.cargo.projector.participant.application.port.in.UseCases
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.event.ApplicationStartupEvent

import javax.inject.Inject
import javax.inject.Singleton

/**
 * This class triggers the participants replay mechanism at startup
 *
 * @since 0.1.0
 */
@Slf4j
@Singleton
class ParticipantReplay implements ApplicationEventListener<ApplicationStartupEvent> {

    @Inject
    UseCases.ReplayEventsCommand replayEventsCommand

    @Override
    void onApplicationEvent(ApplicationStartupEvent event) {
        replayEventsCommand.replay()
    }
}
