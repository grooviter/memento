package memento.gs

import io.grooviter.memento.model.Aggregate
import io.grooviter.memento.model.Event

/**
 * Represents a text document
 *
 * @since 0.1.0
 */
class Document extends Aggregate {
    String title, author, content = ''

    static class Created extends Event {
        String title, author
    }

    static class Appended extends Event {
        String content
    }

    static Document create(String title, String author) {
        Document document = new Document(id: UUID.randomUUID())
        return document.apply(new Created(title: title, author: author, version: document.nextVersion))
    }

    Document append(String content) {
        return this.apply(new Appended(content: content, version: nextVersion))
    }

    private Document apply(Created created) {
        super.apply(created)
        this.title = created.title
        this.author = created.author
        return this
    }

    private Document apply(Appended appended) {
        super.apply(appended)
        this.content += appended.content
        return this
    }
}
