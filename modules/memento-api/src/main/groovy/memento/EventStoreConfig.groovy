package memento

import groovy.transform.builder.Builder

@Builder
class EventStoreConfig {
    EventStoragePort eventStorage
    EventBusPort eventBus
    EventSerdePort serde
    int snapshotThreshold
}
