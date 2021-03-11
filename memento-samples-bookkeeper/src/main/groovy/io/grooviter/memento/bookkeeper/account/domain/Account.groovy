package io.grooviter.memento.bookkeeper.account.domain

import io.grooviter.memento.bookkeeper.account.domain.errors.NotEmptyAccount
import io.grooviter.memento.bookkeeper.account.domain.errors.NotEnoughFunds
import io.grooviter.memento.bookkeeper.account.domain.events.CommissionApplied
import io.grooviter.memento.bookkeeper.account.domain.events.Created
import io.grooviter.memento.bookkeeper.account.domain.events.Closed
import io.grooviter.memento.bookkeeper.account.domain.events.DepositMade
import io.grooviter.memento.bookkeeper.account.domain.events.Events
import io.grooviter.memento.bookkeeper.account.domain.events.WithdrawalMade
import io.grooviter.memento.model.Aggregate

class Account extends Aggregate {
    String iban, name
    Boolean closed = Boolean.FALSE
    BigDecimal balance = BigDecimal.ZERO

    static Account create(String iban, String holderName) {
        Account account = new Account(id: UUID.randomUUID())
        return account.apply(Events.created(iban, holderName, account.nextVersion))
    }

    Account deposit(BigDecimal amount) {
        return this.apply(Events.depositMade(amount, this.balance.add(amount), nextVersion))
    }

    Account withdrawal(BigDecimal amount) {
        if (this.balance.subtract(amount).signum() == -1) {
            throw new NotEnoughFunds()
        }

        return this.apply(Events.withdrawal(amount, this.balance.subtract(amount), nextVersion))
    }

    Account close() {
        if (this.balance >= 0) {
            throw new NotEmptyAccount()
        }

        return this.apply(Events.close(nextVersion))
    }

    Account commission(BigDecimal amount) {
        return this.apply(Events.commission(amount, this.balance.subtract(amount), nextVersion))
    }

    Account apply(Created created) {
        super.apply(created)
        this.iban = created.iban
        this.name = created.name
        return this
    }

    Account apply(Closed deleted) {
        super.apply(deleted)
        this.closed = Boolean.TRUE
        return this
    }

    Account apply(DepositMade depositMade) {
        super.apply(depositMade)
        this.balance = depositMade.balance
        return this
    }

    Account apply(CommissionApplied commissionApplied) {
        super.apply(commissionApplied)
        this.balance = commissionApplied.balance
        return this
    }

    Account apply(WithdrawalMade withdrawalMade) {
        super.apply(withdrawalMade)
        this.balance = withdrawalMade.balance
        return this
    }
}
