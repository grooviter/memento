package io.grooviter.memento.bookkeeper.userarea.domain.vo

import java.time.OffsetDateTime

class AccountInfo {
    UUID accountId
    String holderName
    OffsetDateTime createdAt
}
