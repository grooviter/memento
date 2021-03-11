package io.grooviter.memento.bookkeeper.projector.domain

import groovy.transform.builder.Builder

import java.time.OffsetDateTime

@Builder
class AccountCreated {
    UUID accountId
    String holderName
    OffsetDateTime createdAt
}
