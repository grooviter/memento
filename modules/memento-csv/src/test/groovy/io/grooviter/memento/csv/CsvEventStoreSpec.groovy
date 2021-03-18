package io.grooviter.memento.csv

import io.grooviter.memento.EventStore
import io.grooviter.memento.Memento
import io.grooviter.memento.tck.EventStoreTck
import io.grooviter.memento.tck.fixtures.Fixtures
import spock.lang.Specification

import java.time.OffsetDateTime

import static io.grooviter.memento.tck.Utils.tempEventsFile
import static io.grooviter.memento.tck.Utils.tempSnapshotsFile

class CsvEventStoreSpec extends Specification {

    def 'check tck compliance'() {
        expect: 'the event-store tck to pass'
        EventStoreTck.check(eventStore)

        where: 'possible forms of setting the memory storage are'
        eventStore << [
                Memento.builder().eventStorage(new CsvEventStorage(tempEventsFile(), tempSnapshotsFile())).build(),
                Memento.build { csvStorage(io.grooviter.memento.tck.Utils.tempEventsFile(), io.grooviter.memento.tck.Utils.tempSnapshotsFile()) },
                Memento.build { eventStorage(new CsvEventStorage(io.grooviter.memento.tck.Utils.tempEventsFile(), io.grooviter.memento.tck.Utils.tempSnapshotsFile())) }
        ]
    }

    void 'check tck compliance with mappings'() {
        given: 'destination files'
        File events = io.grooviter.memento.tck.Utils.tempEventsFile()
        File snapshots = io.grooviter.memento.tck.Utils.tempSnapshotsFile()

        when: 'creating event store with those mappings'
        EventStore eventStore = Memento.builder()
            .jacksonSerde(Fixtures.documentMappings())
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

    void 'check files are created if not already present'() {
        given: 'some non existing files'
        OffsetDateTime now = OffsetDateTime.now()
        String eventsPath = "/tmp/events-${now}.csv"
        String snapshotsPath = "/tmp/snapshots-${now}.csv"

        and: 'an event store'
        EventStore eventStore = Memento.build {
            jacksonSerde()
            csvStorage(eventsPath, snapshotsPath)
            snapshotThreshold(2)
        }

        when: 'applying operations'
        EventStoreTck.check(eventStore)

        then: 'expect the files to be present'
        new File(eventsPath).exists()
        new File(snapshotsPath).exists()
    }

    static List<String> extractEventTypes(List<String> csvLines) {
        return csvLines
            .collect { String line -> line.split('\\|').drop(3).take(1).find() as String }
            .unique()
            .sort()
    }
}
