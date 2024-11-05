package memento.tck.model.events

import groovy.transform.builder.Builder
import memento.model.Event
import memento.tck.model.Document

@Builder(includeSuperProperties = true, excludes = ['id', 'createdAt'])
class DocumentCreated extends Event<Document> {
    String title
    String author
}
