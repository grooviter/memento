package io.grooviter.memento.cargo.projector.delivery.adapter.persistence.repository

import io.grooviter.memento.cargo.projector.delivery.adapter.persistence.entity.DeliveryEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
interface DeliveryRepository extends CrudRepository<DeliveryEntity, UUID> {}
