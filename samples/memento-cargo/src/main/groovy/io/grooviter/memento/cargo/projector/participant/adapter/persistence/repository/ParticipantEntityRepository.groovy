package io.grooviter.memento.cargo.projector.participant.adapter.persistence.repository

import io.grooviter.memento.cargo.projector.participant.adapter.persistence.entity.ParticipantEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
interface ParticipantEntityRepository extends CrudRepository<ParticipantEntity, UUID> {}
