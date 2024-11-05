package memento

import memento.Memento
import memento.impl.JacksonEventSerde
import memento.memory.MemoryEventStorage
import memento.tck.EventStoreTck
import memento.EventStore
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
