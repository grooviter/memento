package memento.tck.fixtures

import memento.model.Mappings
import memento.tck.model.Document
import memento.tck.model.events.ContentModified
import memento.tck.model.events.DocumentCreated
import memento.tck.model.events.DocumentDeleted

final class Fixtures {

    static Document fullLifeCycleDocument() {
        return Document.create("Memento", "Christopher Nolan")
            .append("This is a story about a man")
            .append("who couldn't remember anything")
            .append("until something happened...")
            .delete()
    }

    static Mappings documentMappings() {
        return Mappings.builder()
            .addMapping('DOCUMENT', Document)
            .addMapping('DOCUMENT_CREATED', DocumentCreated)
            .addMapping('DOCUMENT_DELETED', DocumentDeleted)
            .addMapping('DOCUMENT_MODIFIED', ContentModified)
            .build()
    }
}
