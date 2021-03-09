package io.grooviter.memento.impl.memory

import groovy.transform.builder.Builder

import java.time.OffsetDateTime

@Builder
class MemoryEventRow {
    UUID id
    UUID aggregateId
    String type
    Integer version
    String json
    OffsetDateTime createdAt
}
