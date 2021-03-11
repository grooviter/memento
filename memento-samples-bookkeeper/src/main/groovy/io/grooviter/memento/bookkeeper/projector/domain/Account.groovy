package io.grooviter.memento.bookkeeper.projector.domain

import com.opencsv.bean.CsvBindByPosition
import com.opencsv.bean.CsvDate
import groovy.transform.builder.Builder

import java.time.OffsetDateTime

@Builder
class Account {
    @CsvBindByPosition(position = 0)
    String id

    @CsvBindByPosition(position = 1)
    String holderName

    @CsvDate
    @CsvBindByPosition(position = 2)
    OffsetDateTime createdAt
}
