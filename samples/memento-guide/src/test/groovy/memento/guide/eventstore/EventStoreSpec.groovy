package memento.guide.eventstore

import memento.EventStore
import memento.Memento
import memento.csv.CsvEventStorage
import memento.guide.eventstore.events.TicketInfoConfirmed
import memento.guide.eventstore.events.TicketInfoProvided
import memento.guide.eventstore.events.TicketOpened
import memento.guide.eventstore.events.TicketPaymentConfirmed
import memento.guide.eventstore.events.TicketPaymentInfoProvided
import memento.guide.eventstore.events.TicketPaymentSent
import memento.guide.eventstore.events.TicketPurchased
import memento.impl.JacksonEventSerde
import memento.model.Mappings
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class EventStoreSpec extends Specification {

    @Rule
    TemporaryFolder temporaryFolder

    void "default event store"() {
        setup:
        def eventsFile = temporaryFolder.newFile()
        def snapshotsFile = temporaryFolder.newFile()

        when:
        // tag::csvstorage[]
        EventStore eventStore = Memento.builder()
            .eventStorage(new CsvEventStorage(eventsFile, snapshotsFile))
            .onEvent(Object::println)
            .serde(new JacksonEventSerde())
            .build()
        // end::csvstorage[]
        then:
        eventStore
    }

    void "default event store with extension methods"() {
        setup:
        def eventsFile = temporaryFolder.newFile()
        def snapshotsFile = temporaryFolder.newFile()

        when:
        // tag::csvstoragewithextensions[]
        EventStore eventStore = Memento.builder()
            .csvStorage(eventsFile, snapshotsFile)
            .onEvent(Object::println)
            .jacksonSerde()
            .build()
        // end::csvstoragewithextensions[]
        then:
        eventStore
    }

    void "jackson serde mappings"() {
        setup:
        def eventsFile = temporaryFolder.newFile()
        def snapshotsFile = temporaryFolder.newFile()

        // tag::custom_mappings[]
        Mappings customMappings = Mappings.builder()
            .addMapping("TICKET_OPENED", TicketOpened)
            .addMapping("TICKET_INFO_FULFILLED", TicketInfoProvided)
            .addMapping("TICKET_INFO_CONFIRMED", TicketInfoConfirmed)
            .addMapping("TICKET_PAYMENT_INFO", TicketPaymentInfoProvided)
            .addMapping("TICKET_PAYMENT_SENT", TicketPaymentSent)
            .addMapping("TICKET_PAYMENT_CONFIRMED", TicketPaymentConfirmed)
            .addMapping("TICKET_PURCHASED", TicketPurchased)
            .build()
        // end::custom_mappings[]

        // tag::event_store_with_mappings[]
        EventStore eventStore = Memento.builder()
            .csvStorage(eventsFile, snapshotsFile)
            .onEvent(Object::println)
            .jacksonSerde(customMappings)
            .snapshotThreshold(2)
            .build()
        // end::event_store_with_mappings[]

        when:
        def concertTicket = ConcertTicket.open()
            .ticketInfoProvided("John", 2, 12.23)
            .ticketInfoConfirmed(2,12.23)
            .paymentInfoProvided("MASTERCARD", "XXXX-XXXX-XXXX-XXXX")
            .paymentSent()
            .paymentConfirmed()
            .ticketPurchased()

        and:
        eventStore.save(concertTicket)

        then:
        eventStore.listEvents(concertTicket.id).count() == 7
    }
}
