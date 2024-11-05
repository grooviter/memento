package memento.samples.bank.account.application.port

import memento.samples.bank.account.domain.Account
import memento.model.Event

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
