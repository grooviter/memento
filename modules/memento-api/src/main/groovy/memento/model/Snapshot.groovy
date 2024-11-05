package memento.model

import java.time.OffsetDateTime

class Snapshot {
    UUID id
    UUID aggregateId
    Integer version
    OffsetDateTime created_at
}
