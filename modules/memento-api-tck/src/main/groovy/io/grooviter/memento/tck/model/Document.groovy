package io.grooviter.memento.tck.model

import io.grooviter.memento.model.Aggregate
import io.grooviter.memento.tck.model.events.ContentModified
import io.grooviter.memento.tck.model.events.DocumentCreated
import io.grooviter.memento.tck.model.events.DocumentDeleted
import io.grooviter.memento.tck.model.events.Events

//@Aggregate(events = [DocumentCreated, DocumentDeleted, ContentModified])
class Document extends Aggregate {
    String content = "", title = "untitled", author = "unknown"

    static Document create(String title, String author) {
        return new Document(id: UUID.randomUUID()).apply(Events.created(title, author))
    }

    Document append(String content) {
        return this.apply(Events.modified("${this.content} $content"))
    }

    Document delete() {
        return this.apply(Events.deleted())
    }

    Document apply(DocumentCreated documentCreated) {
        super.apply(documentCreated)
        this.title = documentCreated.title
        this.author = documentCreated.author
        return this
    }

    Document apply(ContentModified contentModified) {
        super.apply(contentModified)
        this.content = contentModified.content
        return this
    }

    Document apply(DocumentDeleted documentDeleted) {
        super.apply(documentDeleted)
        return this
    }
}
