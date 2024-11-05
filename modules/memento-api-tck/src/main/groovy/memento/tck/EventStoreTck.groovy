package memento.tck

import memento.EventStore
import memento.tck.checks.StorageCheck

class EventStoreTck {

    static void check(EventStore eventStore) {
        StorageCheck.checkList(eventStore)
        StorageCheck.checkAggregateIntegrity(eventStore)
    }
}
