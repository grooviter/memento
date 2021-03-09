package io.grooviter.memento.tck.checks

import io.grooviter.memento.EventStore
import io.grooviter.memento.tck.fixtures.Fixtures
import io.grooviter.memento.tck.model.Document
import io.grooviter.memento.tck.model.events.ContentModified
import io.grooviter.memento.tck.model.events.DocumentCreated
import io.grooviter.memento.tck.model.events.DocumentDeleted

class StorageCheck {

    static void checkList(EventStore eventStore) {
        Document document = Fixtures.fullLifeCycleDocument()
        eventStore.save(document)

        assert eventStore.listEvents(document.id).count() == 5, "wrong number number of events"
        assert eventStore.listEvents(document.id).any {it instanceof DocumentCreated }
        assert eventStore.listEvents(document.id).any { it instanceof DocumentDeleted }
        assert eventStore.listEvents(document.id).any { it instanceof ContentModified }
    }
}
