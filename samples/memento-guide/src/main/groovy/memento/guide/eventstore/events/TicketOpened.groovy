package memento.guide.eventstore.events

import memento.guide.eventstore.ConcertTicket
import memento.model.Event

class TicketOpened extends Event<ConcertTicket> {
    Date purchaseStartedAt = new Date()
}
