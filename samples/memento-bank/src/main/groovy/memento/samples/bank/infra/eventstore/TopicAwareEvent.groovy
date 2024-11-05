package memento.samples.bank.infra.eventstore

import groovy.transform.Immutable

@Immutable
class TopicAwareEvent {
    String topic
    String json
}
