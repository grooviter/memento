package memento.mn.jdbc.impl.entity

import io.micronaut.data.annotation.AutoPopulated
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.TypeDef
import io.micronaut.data.model.DataType

import java.time.OffsetDateTime

/**
 * Represents a snapshot of a given aggregate in a given
 * point in time
 *
 * @since 0.1.0
 */
@MappedEntity("memento_snapshot")
class SnapshotEntity {

    /**
     * Auto populated id
     *
     * @since 0.1.0
     */
    @Id
    @AutoPopulated
    UUID id

    /**
     * Aggregate id. There MUST be ONLY one entry
     * with the same aggregate id
     *
     * @since 0.1.0
     */
    UUID aggregateId

    /**
     * Type of the aggregate for serialization purposes
     *
     * @since 0.1.0
     */
    String aggregateType

    /**
     * Aggregate content
     *
     * @since 0.1.0
     */
    @TypeDef(type = DataType.JSON)
    String json

    /**
     * From which version the snapshot was taken
     *
     * @since 0.1.0
     */
    Integer version

    /**
     * When was the snapshot taken
     *
     * @since 0.1.0
     */
    @DateCreated
    OffsetDateTime created_at
}
