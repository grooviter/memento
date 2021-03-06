package io.grooviter.memento.bank.account.domain.events

import groovy.transform.builder.Builder
import io.grooviter.memento.model.Event

@Builder(includeSuperProperties = true, excludes = ['id', 'createdAt'])
class Created extends Event {
    String iban, name
}
