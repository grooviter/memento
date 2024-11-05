package memento.model

class SnapshotEvent extends Event {
    Class<? extends Aggregate> aggregateType
}
