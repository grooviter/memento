package io.grooviter.memento.cargo.command.delivery.domain.events

import io.grooviter.memento.cargo.common.domain.events.EventTuning
import io.grooviter.memento.model.Event

@EventTuning
class Loaded extends Event {
    UUID driverId
    String comments
}
