package io.grooviter.memento.cargo.query.participant.adapter.eventstore.replay

import groovy.util.logging.Slf4j
import io.grooviter.memento.cargo.query.participant.application.port.in.UseCases
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.event.ApplicationStartupEvent

import jakarta.inject.Inject
import jakarta.inject.Singleton

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
