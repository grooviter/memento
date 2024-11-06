package memento.guide

import groovy.transform.InheritConstructors
import groovy.transform.TupleConstructor
import memento.model.Aggregate
import memento.model.Event

import static java.util.UUID.randomUUID

@InheritConstructors
class Document extends Aggregate {
    String title, author, content = ''

    @TupleConstructor
    static class Created extends Event<Document> {
        String title, author
    }

    @TupleConstructor
    static class Appended extends Event<Document> {
        String content
    }

    static Document create(String title, String author) {
        return new Document(randomUUID()).apply(new Created(title, author))
    }

    Document append(String content) {
        return this.apply(new Appended(content))
    }

    @Override
    void configure() {
        bind(Created)
        bind(Appended) { Document doc, Appended event ->
            doc.content += event.content
        }
    }
}
