package memento.tck.checks

import memento.EventStore
import memento.tck.fixtures.Fixtures
import memento.tck.model.Document
import memento.tck.model.events.ContentModified
import memento.tck.model.events.DocumentCreated
import memento.tck.model.events.DocumentDeleted

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
