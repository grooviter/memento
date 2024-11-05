package memento.model

import java.time.OffsetDateTime

class Event<T> {
    UUID id = UUID.randomUUID()
    UUID aggregateId
    Integer version
    OffsetDateTime createdAt = OffsetDateTime.now()
}
