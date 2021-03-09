package io.grooviter.memento.model

class SnapshotEvent extends Event {
    Class<? extends Aggregate> aggregateType
}
