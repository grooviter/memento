package io.grooviter.memento.cargo.command.delivery.domain.events

import io.grooviter.memento.cargo.shared.EventTuning
import io.grooviter.memento.model.Event

@EventTuning
class Prepared extends Event {
    UUID employeeId
    String comments
}
