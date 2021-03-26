package io.grooviter.memento.cargo.shared

import groovy.transform.TupleConstructor

@TupleConstructor
class TopicAwareEvent {
    String topic
    String json
}
