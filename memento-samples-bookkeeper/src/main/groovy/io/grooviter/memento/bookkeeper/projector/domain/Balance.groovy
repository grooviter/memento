package io.grooviter.memento.bookkeeper.projector.domain

import com.opencsv.bean.CsvBindByPosition
import com.opencsv.bean.CsvDate
import groovy.transform.builder.Builder

import java.time.OffsetDateTime

@Builder
class Balance {
    @CsvBindByPosition(position = 0)
    UUID accountId

    @CsvBindByPosition(position = 1)
    BigDecimal currentBalance

    @CsvDate("yyyy-MM-dd'T'HH:mm:ssZ")
    @CsvBindByPosition(position = 2)
    OffsetDateTime createdAt
}
