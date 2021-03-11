package io.grooviter.memento.bookkeeper.account.adapter.persistence

import com.google.inject.AbstractModule
import com.google.inject.Provides
import io.grooviter.memento.EventStore
import io.grooviter.memento.bookkeeper.account.application.port.out.AccountPorts
import io.grooviter.memento.bookkeeper.account.domain.Account
import io.grooviter.memento.bookkeeper.account.domain.events.*
import io.grooviter.memento.model.Mappings

class PersistenceModule extends AbstractModule {
    void configure() {
        bind(AccountPorts.SaveAccountPort).to(PersistenceAdapter)
        bind(AccountPorts.LoadAccountPort).to(PersistenceAdapter)
    }

    @Provides
    PersistenceAdapter getPersistenceAdapter(EventStore eventStore) {
        return new PersistenceAdapter(eventStore)
    }

    @Provides
    Mappings providesMappings() {
        return Mappings.builder()
            .addMapping("ACCOUNT", Account)
            .addMapping("ACCOUNT_CREATED", Created)
            .addMapping("ACCOUNT_CLOSED", Closed)
            .addMapping("DEPOSIT_MADE", DepositMade)
            .addMapping("WITHDRAWAL_MADE", WithdrawalMade)
            .addMapping("COMMISSION", CommissionApplied)
            .build()
    }
}
