package memento.guide.eventstore.events

import memento.guide.eventstore.ConcertTicket
import memento.model.Event

class TicketPaymentSent extends Event<ConcertTicket> {}
