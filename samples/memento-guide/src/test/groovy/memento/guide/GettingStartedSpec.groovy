package memento.guide

import groovy.test.GroovyAssert
import spock.lang.Specification

class GettingStartedSpec extends Specification {

    void 'getting started script'() {
        setup: 'csv files to store information'
        def script = '''
        // tag::getting_started_script[]
        import memento.*
        import memento.model.*
        import groovy.transform.*
        import static java.util.UUID.randomUUID
        
        // EVENTS... <1>
        @TupleConstructor
        class Created extends Event<Document> {
            String title, author
        }

        @TupleConstructor        
        class Appended extends Event<Document> {
            String content
        }
        
        // AGGREGATE... <2>
        @InheritConstructors
        class Document extends Aggregate {
            String title, author, content = \'\'
            // EVENT_CONFIGURATION... <3>
            @Override
            void configure() {
                // applies all matching event properties to Aggregate
                bind(Created)
                // applies event to Aggregate as defined in closure
                bind(Appended) { Document doc, Appended event ->
                    doc.content += event.content
                }
            }
        }
        
        // EVENTSTORE IMPLEMENTATION... <4>
        EventStore eventStore = Memento.builder()
            .csvStorage('/tmp/events.csv', '/tmp/snapshots.csv')  // STORAGE
            .jacksonSerde()                                       // SERDE
            .snapshotThreshold(2) // snapshot every 2 events
            .onEvent(Object::println)                             // EVENTBUS
            .build()
        
        // CREATING AND STORING AN AGGREGATE.... <5>
        Document document = new Document(UUID.randomUUID())
            .apply(new Created("Memento", "Christopher Nolan"))
            .apply(new Appended("A man who, as a result of an injury,"))
            .apply(new Appended(", has anterograde amnesia"))
            .apply(new Appended("and has short-term memory loss"))
            .apply(new Appended("approximately every fifteen minutes."))
        
        // SAVING THE AGGREGATE's EVENTS... <6>
        eventStore.save(document)
        // end::getting_started_script[]
        '''

        expect:
        GroovyAssert.assertScript(script)
    }
}
