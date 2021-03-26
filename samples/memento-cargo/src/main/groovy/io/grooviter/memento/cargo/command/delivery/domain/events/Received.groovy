package io.grooviter.memento.cargo.command.delivery.domain.events

import io.grooviter.memento.cargo.shared.EventTuning
import io.grooviter.memento.model.Event

@EventTuning
class Received extends Event {
    UUID clientId
    String comments
}
