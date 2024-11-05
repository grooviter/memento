package memento.samples.cargo.query.delivery.adapter.persistence.repository

import memento.samples.cargo.query.delivery.adapter.persistence.entity.DeliveryEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository

@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
interface DeliveryEntityRepository extends PageableRepository<DeliveryEntity, UUID> {}
