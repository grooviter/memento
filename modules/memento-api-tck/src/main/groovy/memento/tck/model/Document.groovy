package memento.tck.model

import memento.model.Aggregate
import memento.tck.model.events.ContentModified
import memento.tck.model.events.DocumentCreated
import memento.tck.model.events.DocumentDeleted

import static memento.tck.model.events.Events.created
import static memento.tck.model.events.Events.deleted
import static memento.tck.model.events.Events.modified
import static java.util.UUID.randomUUID

class Document extends Aggregate {
    String content = "", title = "untitled", author = "unknown"

    static Document create(String title, String author) {
        return new Document(id: randomUUID()).apply(created(title, author))
    }

    Document append(String content) {
        return this.apply(modified("${this.content} $content"))
    }

    Document delete() {
        return this.apply(deleted())
    }

    void configure() {
        bind(DocumentCreated, ContentModified, DocumentDeleted)
    }
}
