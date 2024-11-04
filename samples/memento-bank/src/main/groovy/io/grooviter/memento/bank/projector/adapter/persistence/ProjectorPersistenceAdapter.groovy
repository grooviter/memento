package io.grooviter.memento.bank.projector.adapter.persistence

import io.grooviter.memento.bank.infra.Configuration
import io.grooviter.memento.bank.infra.persistence.Utils
import io.grooviter.memento.bank.projector.application.port.out.ProjectorPorts
import io.grooviter.memento.bank.projector.domain.Account
import io.grooviter.memento.bank.projector.domain.Balance

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class ProjectorPersistenceAdapter implements
    ProjectorPorts.SaveAccountPort,
    ProjectorPorts.UpdateBalancePort {

    @Inject
    Configuration configuration

    @Override
    void save(Account account) {
        Utils.appendToCsvFile(configuration.projections.accounts, account)
    }

    @Override
    void updateBalance(Balance balance) {
        Utils.appendToCsvFile(configuration.projections.balances, balance)
    }
}
