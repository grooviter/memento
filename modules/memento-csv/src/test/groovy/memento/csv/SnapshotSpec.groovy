package memento.csv

import memento.EventStore
import memento.Memento
import memento.csv.model.CartItem
import memento.csv.model.ShoppingCart
import memento.tck.Utils
import memento.tck.fixtures.Fixtures
import spock.lang.Specification

class SnapshotSpec extends Specification {

    void 'check snapshots works properly'() {
        setup: 'bootstrapping the event store'
        File events = Utils.tempEventsFile()
        File snapshots = Utils.tempSnapshotsFile()
        EventStore eventStore = Memento.builder()
            .jacksonSerde(Fixtures.documentMappings())
            .csvStorage(events, snapshots)
            .snapshotThreshold(2)
            .onEvent(event -> "on event $event")
            .build()

        when: 'creating events to create a few snapshots'
        ShoppingCart shoppingCart = ShoppingCart
            .create()
            .addItem(new CartItem(description: "A"))
            .addItem(new CartItem(description: "B"))
            .addItem(new CartItem(description: "C"))
            .addItem(new CartItem(description: "D"))
        eventStore.save(shoppingCart)

        and: 'adding more events to create more snapshots'
        eventStore
            .load(shoppingCart.id, ShoppingCart)
            .map(sc -> sc
                .addItem(new CartItem(description: "E"))
                .addItem(new CartItem(description: "F"))
                .addItem(new CartItem(description: "G"))
                .addItem(new CartItem(description: "H"))
                .addItem(new CartItem(description: "I"))
                .addItem(new CartItem(description: "J")))
            .ifPresent(eventStore::save)

        and: 'saving the event and gathering resulting data'
        List<Map> eventLines = events
            .readLines()
            .collect(this::toEvent)
            .sort(event -> Integer.parseInt("${event.version}"))

        List<Map> snapshotLines = snapshots
            .readLines()
            .collect(this::toSnapshot)
            .sort(snapshot -> Integer.parseInt("${snapshot.version}"))

        then: 'we should get two snapshots and the last should match the last event version'
        with(snapshotLines) {
            size() == 2
            last().version == eventLines.last().version
        }
    }

    private static Map toEvent(String line) {
        def (id,aggregateId,version,type,json,date) = line.split('\\|')
        return [
            id: id,
            aggregateId: aggregateId,
            version: version,
            type: type,
            json: json,
            date: date
        ]
    }

    private static Map toSnapshot(String line) {
        def (id, aggregateId,type,version,json,date) = line.split('\\|')
        return [
            id: id,
            aggregateId: aggregateId,
            version: version,
            type: type,
            json: json,
            date: date
        ]
    }
}
