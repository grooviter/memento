package io.grooviter.memento.bank.account.application.port.out

import io.grooviter.memento.bank.account.domain.Account
import io.grooviter.memento.model.Event

import java.time.OffsetDateTime

final class AccountPorts {

    interface LoadAccountPort {
        Optional<Account> load(UUID id)
    }

    interface SaveAccountPort {
        void save(Account account)
    }

    interface ListTransactions {
        List<Event> list(OffsetDateTime from, OffsetDateTime to)
    }
}
