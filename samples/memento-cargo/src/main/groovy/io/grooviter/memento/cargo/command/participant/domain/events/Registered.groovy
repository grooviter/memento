package io.grooviter.memento.cargo.command.participant.domain.events

import io.grooviter.memento.cargo.command.participant.domain.ParticipantRole
import io.grooviter.memento.cargo.common.domain.events.EventTuning
import io.grooviter.memento.model.Event

@EventTuning
class Registered extends Event {
    String name
    ParticipantRole role
}
