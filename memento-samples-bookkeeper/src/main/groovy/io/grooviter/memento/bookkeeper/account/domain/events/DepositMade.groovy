package io.grooviter.memento.bookkeeper.account.domain.events

import groovy.transform.builder.Builder
import io.grooviter.memento.model.Event

@Builder(includeSuperProperties = true, excludes = ['id'])
class DepositMade extends Event {
    BigDecimal amount
    BigDecimal balance
}
