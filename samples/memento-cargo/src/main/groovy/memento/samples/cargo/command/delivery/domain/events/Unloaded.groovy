package memento.samples.cargo.command.delivery.domain.events

import memento.samples.cargo.command.delivery.domain.Delivery
import memento.samples.cargo.shared.EventTuning
import memento.model.Event

@EventTuning
class Unloaded extends Event<Delivery> {
    UUID driverId
    String comments
}
