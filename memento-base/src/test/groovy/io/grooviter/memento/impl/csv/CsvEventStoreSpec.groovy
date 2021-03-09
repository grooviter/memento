package io.grooviter.memento.impl.csv

import io.grooviter.memento.EventStore
import io.grooviter.memento.Memento
import io.grooviter.memento.tck.EventStoreTck
import io.grooviter.memento.tck.fixtures.Fixtures
import spock.lang.Specification

import static io.grooviter.memento.tck.Utils.tempEventsFile
import static io.grooviter.memento.tck.Utils.tempSnapshotsFile

class CsvEventStoreSpec extends Specification {

    def 'check tck compliance'() {
        expect: 'the event-store tck to pass'
        EventStoreTck.check(eventStore)

        where: 'possible forms of setting the memory storage are'
        eventStore << [
            Memento.builder().eventStorage(new CsvEventStorage(tempEventsFile(), tempSnapshotsFile())).build(),
            Memento.build { csvStorage(tempEventsFile(), tempSnapshotsFile()) },
            Memento.build { eventStorage(new CsvEventStorage(tempEventsFile(), tempSnapshotsFile())) }
        ]
    }

    void 'check tck compliance with mappings'() {
        given: 'destination files'
        File events = tempEventsFile()
        File snapshots = tempSnapshotsFile()

        when: 'creating event store with those mappings'
        EventStore eventStore = Memento.builder()
            .groovySerde(Fixtures.documentMappings())
            .csvStorage(events, snapshots)
            .snapshotThreshold(2)
            .build()

        and: 'all tck checks should be passed'
        EventStoreTck.check(eventStore)
        List<String> eventTypes = extractEventTypes(events.readLines())

        then: 'both files should exist'
        events.exists()
        snapshots.exists()

        and: 'the events should be mapped to their aliases'
        eventTypes == ['DOCUMENT_CREATED', 'DOCUMENT_DELETED', 'DOCUMENT_MODIFIED']
    }

    static List<String> extractEventTypes(List<String> csvLines) {
        return csvLines
            .collect { String line -> line.split('\\|').drop(3).take(1).find() as String }
            .unique()
            .sort()
    }
}
