package io.grooviter.memento.cargo.common.domain.events

import groovy.transform.TupleConstructor

@TupleConstructor
class TopicAwareEvent {
    String topic
    String json
}
