package memento.guide.eventstore.events

import groovy.transform.TupleConstructor
import memento.guide.eventstore.ConcertTicket
import memento.model.Event

@TupleConstructor
 class TicketInfoConfirmed extends Event<ConcertTicket> {
    Double price
    Integer noTickets
}
