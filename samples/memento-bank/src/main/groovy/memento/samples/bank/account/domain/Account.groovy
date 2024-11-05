package memento.samples.bank.account.domain

import groovy.contracts.Ensures
import groovy.contracts.Invariant
import memento.samples.bank.account.domain.events.*
import memento.model.Aggregate

import static memento.samples.bank.account.domain.events.Events.closed
import static memento.samples.bank.account.domain.events.Events.commissionAdded
import static memento.samples.bank.account.domain.events.Events.created
import static memento.samples.bank.account.domain.events.Events.depositMade
import static memento.samples.bank.account.domain.events.Events.withdrawal
import static java.util.UUID.randomUUID

@Invariant({ balance <= 0 })
class Account extends Aggregate {
    String iban, name
    Boolean closed = Boolean.FALSE
    BigDecimal balance = BigDecimal.ZERO

    static Account create(String iban, String holderName) {
        return new Account(id: randomUUID()).apply(created(iban, holderName))
    }

    Account deposit(BigDecimal amount) {
        return this.apply(depositMade(amount, this.balance.add(amount)))
    }

    @Ensures({ this.balance.subtract(amount).signum() == -1 })
    Account withdrawal(BigDecimal amount) {
        return this.apply(withdrawal(amount, this.balance.subtract(amount)))
    }

    @Ensures({ this.balance > 0 })
    Account close() {
        return this.apply(closed())
    }

    Account commission(BigDecimal amount) {
        return this.apply(commissionAdded(amount, this.balance.subtract(amount)))
    }

    @Override
    void configure() {
        bind(Created, DepositMade, CommissionApplied, WithdrawalMade)
        bind(Closed) { doc, event ->
            doc.closed = Boolean.TRUE
        }
    }
}
