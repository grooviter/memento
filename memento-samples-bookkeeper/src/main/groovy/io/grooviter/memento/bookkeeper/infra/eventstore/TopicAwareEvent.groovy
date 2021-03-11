package io.grooviter.memento.bookkeeper.infra.eventstore

import groovy.transform.Immutable
import groovy.transform.TupleConstructor

@Immutable
class TopicAwareEvent {
    String topic
    String json
}
