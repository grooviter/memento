package memento.mn.jdbc.impl.repository


import io.micronaut.data.annotation.Repository
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

/**
 * Responsible for persisting aggregate snapshots
 *
 * @since 0.1.0
 */
@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
interface SnapshotRepository extends CrudRepository<memento.mn.jdbc.impl.entity.SnapshotEntity, UUID> {
    /**
     * Finds the last snapshot of an aggregate by its aggregate id
     *
     * @param aggregateId the id of the aggregate we want the snapshot from
     * @return an optional {@link memento.mn.jdbc.impl.entity.SnapshotEntity}
     * @since 0.1.0
     */
    Optional<memento.mn.jdbc.impl.entity.SnapshotEntity> findByAggregateIdOrderByVersionDesc(UUID aggregateId)
}
