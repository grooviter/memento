package io.grooviter.memento.mn.impl.entity

import io.micronaut.data.annotation.AutoPopulated
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.TypeDef
import io.micronaut.data.model.DataType

import java.time.OffsetDateTime

/**
 * Represents an event store entry
 *
 * @since 0.1.0
 */
@MappedEntity("memento")
class EventEntity {

    /**
     * Auto populated id
     *
     * @since 0.1.0
     */
    @Id
    @AutoPopulated
    UUID id

    /**
     * The aggregate id. There must be only one entry
     * with a given aggregate id and version
     *
     * @since 0.1.0
     */
    UUID aggregateId

    /**
     * Type of the event
     *
     * @since 0.1.0
     */
    String type

    /**
     * Version of the aggregate
     *
     * @since 0.1.0
     */
    Integer version

    /**
     * Event content
     *
     * @since 0.1.0
     */
    @TypeDef(type = DataType.JSON)
    String json

    /**
     * When the event happened
     *
     * @since 0.1.0
     */
    @DateCreated
    OffsetDateTime createdAt
}
