package io.grooviter.memento.tck.fixtures

import io.grooviter.memento.model.Mappings
import io.grooviter.memento.tck.model.Document
import io.grooviter.memento.tck.model.events.ContentModified
import io.grooviter.memento.tck.model.events.DocumentCreated
import io.grooviter.memento.tck.model.events.DocumentDeleted

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
