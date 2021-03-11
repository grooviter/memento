package io.grooviter.memento.bookkeeper.account.adapter.persistence

import io.grooviter.memento.EventStore
import io.grooviter.memento.bookkeeper.account.application.port.out.AccountPorts
import io.grooviter.memento.bookkeeper.account.domain.Account

class PersistenceAdapter implements
    AccountPorts.SaveAccountPort,
    AccountPorts.LoadAccountPort {
    EventStore eventStore

    PersistenceAdapter(EventStore eventStore) {
        this.eventStore = eventStore
    }

    @Override
    void save(Account account) {
        this.eventStore.save(account)
    }

    @Override
    Optional<Account> load(UUID id) {
        return this.eventStore.load(id, Account)
    }
}
