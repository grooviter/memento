package io.grooviter.memento.tck.model

import io.grooviter.memento.model.Aggregate
import io.grooviter.memento.tck.model.events.ContentModified
import io.grooviter.memento.tck.model.events.DocumentCreated
import io.grooviter.memento.tck.model.events.DocumentDeleted

class Document extends Aggregate {
    String content = ""
    String title = "untitled"
    String author = "unknown"

    static Document create(String title, String author) {
        Document document = new Document(id: UUID.randomUUID())
        DocumentCreated documentCreated =
            new DocumentCreated(title: title, author: author, version: document.nextVersion)

        document.apply(documentCreated)
        return document
    }

    Document append(String content) {
        this.apply(new ContentModified(content: "${this.content} $content", version: nextVersion))
        return this
    }

    Document delete() {
        this.apply(new DocumentDeleted(version: nextVersion))
        return this
    }

    void apply(DocumentCreated documentCreated) {
        super.apply(documentCreated)
        this.title = documentCreated.title
        this.author = documentCreated.author
    }

    void apply(ContentModified contentModified) {
        super.apply(contentModified)
        this.content = contentModified.content
    }

    void apply(DocumentDeleted documentDeleted) {
        super.apply(documentDeleted)
    }
}
