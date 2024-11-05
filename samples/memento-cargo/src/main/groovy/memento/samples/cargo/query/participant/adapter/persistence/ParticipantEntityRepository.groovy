package memento.samples.cargo.query.participant.adapter.persistence

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository

@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
interface ParticipantEntityRepository extends PageableRepository<ParticipantEntity, UUID> {
}
