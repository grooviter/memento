package memento.samples.bank.account.adapter.persistence

import memento.EventStore
import memento.samples.bank.account.application.port.out.AccountPorts
import memento.samples.bank.account.domain.Account

import jakarta.inject.Singleton

@Singleton
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
