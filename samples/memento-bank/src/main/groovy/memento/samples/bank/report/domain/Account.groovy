package memento.samples.bank.report.domain

import com.opencsv.bean.CsvBindByPosition
import com.opencsv.bean.CsvDate
import groovy.transform.builder.Builder

import java.time.OffsetDateTime

@Builder
class Account {
    @CsvBindByPosition(position = 0)
    String id

    @CsvBindByPosition(position = 1)
    String name

    @CsvDate("yyyy-MM-dd'T'HH:mm:ssZ")
    @CsvBindByPosition(position = 2)
    OffsetDateTime createdAt
}