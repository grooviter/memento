package memento.guide.eventstore.events

import memento.guide.eventstore.ConcertTicket
import memento.model.Event

class TicketPurchased extends Event<ConcertTicket> {
    Date purchaseClosedAt = new Date()
}
