package io.grooviter.memento.bank.account.domain.events

import groovy.transform.builder.Builder
import io.grooviter.memento.bank.account.domain.Account
import io.grooviter.memento.model.Event

@Builder(includeSuperProperties = true, excludes = ['id', 'createdAt'])
class Closed extends Event<Account> {
}
