package io.grooviter.memento.mn.impl.repository

import io.grooviter.memento.mn.impl.entity.EventEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository

import java.util.stream.Stream

/**
 * Responsible for persisting events in the underlying database
 *
 * @since 0.1.0
 */
@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
abstract class EventRepository implements PageableRepository<EventEntity, UUID> {

    /**
     * Alias for saving a new event to the databae
     *
     * @param event the event to persist
     * @since 0.1.0
     */
    void append(EventEntity event) {
        this.save(event)
    }

    /**
     * Retrieves all events from a given aggregate from the the beginning
     *
     * @param aggregateId aggregate we want the events from
     * @return an {@link Stream} of {@link EventEntity} instances
     * @since 0.1.0
     */
    abstract Stream<EventEntity> findAllByAggregateId(UUID aggregateId)

    /**
     * Retrieves all events from a given aggregate from a specific version
     *
     * @param aggregateId aggregate we want the events from
     * @param version initial point
     * @return an {@link Stream} of {@link EventEntity} instances
     * @since 0.1.0
     */
    abstract Stream<EventEntity> findAllByAggregateIdAndVersionGreaterThanOrderByVersion(
        UUID aggregateId,
        Integer version)

    /**
     * Retrieves all events of a given type
     *
     * @param aliases list with names of the type of the events
     * @return an stream of events of the types passed as arguments
     * @since 0.1.0
     */
    abstract Stream<EventEntity> findAllByTypeIn(String[] aliases)
}
