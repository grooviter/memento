package memento.guide.eventstore.events

import groovy.transform.TupleConstructor
import memento.guide.eventstore.ConcertTicket
import memento.model.Event

@TupleConstructor
 class TicketInfoProvided extends Event<ConcertTicket> {
    String buyerName
    Integer noTickets
    Double price
}
