package io.grooviter.memento.tck

import io.grooviter.memento.EventStore
import io.grooviter.memento.tck.checks.StorageCheck

class EventStoreTck {

    static void check(EventStore eventStore) {
        StorageCheck.checkList(eventStore)
    }
}
