package memento.samples.bank.account.domain.events

import groovy.transform.builder.Builder
import memento.samples.bank.account.domain.Account
import memento.model.Event

@Builder(includeSuperProperties = true, excludes = ['id', 'createdAt'])
class DepositMade extends Event<Account> {
    BigDecimal amount
    BigDecimal balance
}
