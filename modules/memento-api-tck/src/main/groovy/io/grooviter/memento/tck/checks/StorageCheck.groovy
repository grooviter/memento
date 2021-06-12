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

        assert document.version == 5
        assert eventStore.listEvents(document.id).count() == 5, "wrong number number of events"
        assert eventStore.listEvents(document.id).any {it instanceof DocumentCreated }
        assert eventStore.listEvents(document.id).any { it instanceof DocumentDeleted }
        assert eventStore.listEvents(document.id).any { it instanceof ContentModified }
    }

    static void checkAggregateIntegrity(EventStore eventStore) {
        Document document = Fixtures.fullLifeCycleDocument()
        eventStore.save(document)
        Document retrieved = eventStore.load(document.id, Document).get()

        assert retrieved.id
        assert retrieved.title
        assert retrieved.author
        assert retrieved.content
    }
}
