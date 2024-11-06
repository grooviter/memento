package memento.guide

import memento.EventStore
import memento.Memento
import memento.model.Mappings
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class GettingStartedSpec extends Specification {

    @Rule
    TemporaryFolder temporaryFolder

    // tag::jacksonmappings[]
    static final MAPPINGS = Mappings.builder()
        .addMapping("DOC_CREATED", Document.Created)
        .addMapping("DOC_APPENDED", Document.Appended)
        .addMapping("DOCUMENT", Document)
        .build()
    // end::jacksonmappings[]

    void 'store events in csv file'() {
        setup: 'csv files to store information'
        File eventsFile = temporaryFolder.newFile()
        File snapshotsFile = temporaryFolder.newFile()

        and: 'an event store implementation'
        // tag::defaulteventstore[]
        EventStore eventStore = Memento.builder()
                .csvStorage(eventsFile, snapshotsFile)
                .jacksonSerde()
                .onEvent { println(it) }
                .build()
        // end::defaulteventstore[]

        and: 'a given document'
        // tag::insertevents[]
        Document document = Document.create("My Book", "Peter Me")
                .append("This is the story of ")
                .append("someone")
        // end::insertevents[]

        when: 'saving the aggregate'
        // tag::saveaggregate[]
        eventStore.save(document)
        // end::saveaggregate[]

        and: 'recreate document'
        Document loaded = eventStore.load(document.id, Document).get()

        then: 'we should get that only one event happened'
        loaded.version          == 3

        and: 'no events to apply'
        loaded.eventList.size() == 0

        and: 'the loaded aggregate should have the expected data'
        loaded.id
        loaded.title   == 'My Book'
        loaded.author  == 'Peter Me'
        loaded.content == 'This is the story of someone'

        and: 'the number of stored events are the expected'
        eventsFile.readLines().size() == 3

        cleanup:
        println(eventsFile.text)
    }

    void 'store events in csv file with custom mappings'() {
        given: 'csv files to store information'
        File eventsFile = temporaryFolder.newFile()
        File snapshotsFile = temporaryFolder.newFile()

        and: 'an event store implementation'
        // tag::jacksoneventstore[]
        EventStore eventStore = Memento.builder()
            .csvStorage(eventsFile, snapshotsFile)
            .jacksonSerde(MAPPINGS)
            .onEvent(event -> println("broadcasted-event: ${event.class.simpleName}"))
            .build()
        // end::jacksoneventstore[]
        and: 'a given document'
        Document document = Document.create("My Book", "Peter Me")
            .append("This is the story of ")
            .append("someone")

        when: 'saving the aggregate'
        eventStore.save(document)

        and: 'recreate document'
        Document loaded = eventStore.load(document.id, Document).get()

        then: 'we should get that only one event happened'
        loaded.version          == 3

        and: 'no events to apply'
        loaded.eventList.size() == 0

        and: 'the loaded aggregate should have the expected data'
        loaded.id
        loaded.title   == 'My Book'
        loaded.author  == 'Peter Me'
        loaded.content == 'This is the story of someone'

        and: 'the number of stored events are the expected'
        eventsFile.readLines().size() == 3

        cleanup:
        println(eventsFile.text)
    }
}
