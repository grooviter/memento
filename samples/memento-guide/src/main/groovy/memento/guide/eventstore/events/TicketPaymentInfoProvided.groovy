package memento.guide.eventstore.events

import groovy.transform.TupleConstructor
import memento.guide.eventstore.ConcertTicket
import memento.model.Event

@TupleConstructor
class TicketPaymentInfoProvided extends Event<ConcertTicket> {
    String cardType
    String cardId
}
