package memento.samples.bank.account.domain.events

import groovy.transform.builder.Builder
import memento.samples.bank.account.domain.Account
import memento.model.Event

@Builder(includeSuperProperties = true, excludes = ['id', 'createdAt'])
class Created extends Event<Account> {
    String iban, name
}
