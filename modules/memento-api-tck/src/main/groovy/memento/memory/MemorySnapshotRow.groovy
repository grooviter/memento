package memento.memory

import groovy.transform.ToString
import groovy.transform.builder.Builder

import java.time.OffsetDateTime

@ToString(includeNames = true)
@Builder
class MemorySnapshotRow {
    UUID id
    UUID aggregateId
    String aggregateType
    Integer version
    String json
    OffsetDateTime createdAt
}
