package io.grooviter.memento

import io.grooviter.memento.tck.EventStoreTck
import spock.lang.Specification

class MementoSpec extends Specification {

    void 'check sane defaults with builder()'() {
        when: 'creating a default event-store'
        EventStore eventStore = Memento.builder().build()

        then: 'all tck checks should be passed'
        EventStoreTck.check(eventStore)
    }

    void 'check sane defaults with build()'() {
        when: 'creating a default event-store'
        EventStore eventStore = Memento.build {}

        then: 'all tck checks should be passed'
        EventStoreTck.check(eventStore)
    }
}
