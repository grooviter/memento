package memento.guide.eventstore

import groovy.contracts.Ensures
import groovy.transform.InheritConstructors
import memento.guide.eventstore.events.TicketInfoConfirmed
import memento.guide.eventstore.events.TicketInfoProvided
import memento.guide.eventstore.events.TicketOpened
import memento.guide.eventstore.events.TicketPaymentConfirmed
import memento.guide.eventstore.events.TicketPaymentInfoProvided
import memento.guide.eventstore.events.TicketPaymentSent
import memento.guide.eventstore.events.TicketPurchased
import memento.model.Aggregate

import static java.util.UUID.randomUUID

@InheritConstructors
class ConcertTicket extends Aggregate {
    String buyerName
    Double price
    Integer noTickets
    Date purchaseStartedAt
    Date purchaseClosedAt

    static ConcertTicket open() {
        return new ConcertTicket(randomUUID()).apply(new TicketOpened())
    }

    @Ensures({ !buyerName?.empty && noTickets >= 1 && price > 0 })
    ConcertTicket ticketInfoProvided(String buyerName, Integer noTickets, Double price) {
        return this.apply(new TicketInfoProvided(buyerName, noTickets, price))
    }

    @Ensures({ noTickets >= 1 && price > 0 })
    ConcertTicket ticketInfoConfirmed(Integer noTickets, Double price) {
        return this.apply(new TicketInfoConfirmed(price, noTickets))
    }

    @Ensures({ cardIdIsValid(cardId) })
    ConcertTicket paymentInfoProvided(String cardType, String cardId) {
        return this.apply(new TicketPaymentInfoProvided(cardType, cardId))
    }

    ConcertTicket paymentSent() {
        return this.apply(new TicketPaymentSent())
    }

    ConcertTicket paymentConfirmed() {
        return this.apply(new TicketPaymentConfirmed())
    }

    ConcertTicket ticketPurchased() {
        return this.apply(new TicketPurchased())
    }

    static boolean cardIdIsValid(String cardID) {
        return cardID ==~ /....-....-....-..../
    }

    @Override
    void configure() {
        bind(
            TicketOpened,
            TicketInfoProvided,
            TicketInfoConfirmed,
            TicketPaymentInfoProvided,
            TicketPaymentSent,
            TicketPaymentConfirmed,
            TicketPurchased
        )
    }
}
