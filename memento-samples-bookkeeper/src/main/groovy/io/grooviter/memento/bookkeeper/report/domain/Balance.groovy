package io.grooviter.memento.bookkeeper.report.domain

import com.opencsv.bean.CsvBindByPosition
import com.opencsv.bean.CsvDate

import java.time.OffsetDateTime

class Balance {
    @CsvBindByPosition(position = 0)
    String accountId

    @CsvBindByPosition(position = 1)
    BigDecimal balance

    @CsvDate("yyyy-MM-dd'T'HH:mm:ssZ")
    @CsvBindByPosition(position = 2)
    OffsetDateTime createdAt
}
