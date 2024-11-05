package memento.samples.cargo.command.participant.domain.events

import memento.samples.cargo.command.participant.domain.ParticipantRole
import memento.samples.cargo.shared.EventTuning
import memento.model.Event

@EventTuning
class Registered extends Event {
    String name
    ParticipantRole role
}
