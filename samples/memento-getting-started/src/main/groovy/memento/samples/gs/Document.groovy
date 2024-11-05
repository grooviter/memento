package memento.samples.gs

import memento.EventStore
import memento.Memento
import memento.model.Aggregate
import memento.model.Event

import static java.util.UUID.randomUUID


class Document extends Aggregate {
    String title, author, content = ''

    static class Created extends Event<Document> {
        String title, author
    }

    static class Appended extends Event<Document> {
        String content
    }

    static Document create(String title, String author) {
        return new Document(id: randomUUID()).apply(new Created(title: title, author: author))
    }

    Document append(String content) {
        return this.apply(new Appended(content: content))
    }

    @Override
    void configure() {
        bind(Created)
        bind(Appended) { Document doc, Appended event ->
            doc.content += event.content
        }
    }

    static void main(String[] args) {
        // EVENTSTORE IMPLEMENTATION...
        EventStore eventStore = Memento.builder()
                .csvStorage('/tmp/events.csv', '/tmp/snapshots.csv')
                .snapshotThreshold(2) // snapshot every 2 events
                .build()

        // CREATING AND STORING AN AGGREGATE....
        Document document = Document.create("Memento", "Christopher Nolan")
                .append("A man who, as a result of an injury,")
                .append(", has anterograde amnesia")
                .append("and has short-term memory loss")
                .append("approximately every fifteen minutes.")

        // SAVING THE AGGREGATE's EVENTS...
        eventStore.save(document)
    }
}
