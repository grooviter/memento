package io.grooviter.memento.cargo.command.delivery.domain.events

import io.grooviter.memento.cargo.command.delivery.domain.Delivery
import io.grooviter.memento.cargo.shared.EventTuning
import io.grooviter.memento.model.Event

@EventTuning
class Loaded extends Event<Delivery> {
    UUID driverId
    String comments
}
