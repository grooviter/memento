package io.grooviter.memento

import io.grooviter.memento.impl.JacksonEventSerde
import io.grooviter.memento.memory.MemoryEventStorage
import io.grooviter.memento.tck.EventStoreTck
import spock.lang.Specification

class MementoSpec extends Specification {

    void 'check sane defaults with builder()'() {
        when: 'creating a default event-store'
        EventStore eventStore = Memento
            .builder()
            .eventStorage(new MemoryEventStorage())
            .serde(new JacksonEventSerde())
            .onEvent(event -> println(event))
            .build()

        then: 'all tck checks should be passed'
        EventStoreTck.check(eventStore)
    }
}
