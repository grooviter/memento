package memento.samples.gs

import memento.model.Event
import memento.samples.gs.Document
import spock.lang.Specification

class DocumentSpec extends Specification {

    def 'create a new document'() {
        when: 'creating a document'
        Document document = Document.create("An interesting book", "Anonymous")
            .append("This is the story ")
            .append("of a simple man")

        and:
        List<Event> events = document.eventList

        then: 'we should get the expected data'
        with(document) {
            title   == 'An interesting book'
            author  == 'Anonymous'
            content == 'This is the story of a simple man'
            version == 3
        }

        and: 'the size of the vents should be equal to aggregate version'
        with(events) {
            size() == 3
        }
    }
}
