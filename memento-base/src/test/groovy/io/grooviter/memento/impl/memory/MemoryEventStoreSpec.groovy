package io.grooviter.memento.impl.memory

import io.grooviter.memento.EventStore
import io.grooviter.memento.Memento
import io.grooviter.memento.tck.EventStoreTck
import io.grooviter.memento.tck.fixtures.Fixtures
import spock.lang.Specification

class MemoryEventStoreSpec extends Specification {

    def 'check tck compliance'() {
        expect: 'the event-store tck to pass'
        EventStoreTck.check(eventStore)

        where: 'possible forms of setting the memory storage are'
        eventStore << [
            Memento.builder().build(),
            Memento.builder().memoryStorage().build(),
            Memento.builder().eventStorage(new MemoryEventStorage()).build(),
            Memento.build {},
            Memento.build { memoryStorage() },
            Memento.build { eventStorage(new MemoryEventStorage()) }
        ]
    }

    void 'check tck compliance with mappings'() {
        when: 'creating event store with those mappings'
        EventStore eventStore = Memento.builder()
                .jacksonSerde(Fixtures.documentMappings())
                .memoryStorage()
                .snapshotThreshold(2)
                .build()

        then: 'all tck checks should be passed'
        EventStoreTck.check(eventStore)
    }
}
