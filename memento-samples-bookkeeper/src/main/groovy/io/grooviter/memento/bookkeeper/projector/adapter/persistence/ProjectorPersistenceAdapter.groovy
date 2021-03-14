package io.grooviter.memento.bookkeeper.projector.adapter.persistence

import io.grooviter.memento.bookkeeper.infra.Configuration
import io.grooviter.memento.bookkeeper.infra.persistence.Utils
import io.grooviter.memento.bookkeeper.projector.application.port.out.ProjectorPorts
import io.grooviter.memento.bookkeeper.projector.domain.Account
import io.grooviter.memento.bookkeeper.projector.domain.Balance

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectorPersistenceAdapter implements
    ProjectorPorts.SaveAccountPort,
    ProjectorPorts.UpdateBalancePort {

    @Inject
    Configuration configuration

    @Override
    void save(Account account) {
        Utils.writeToCsvFile(configuration.projections.accounts, account)
    }

    @Override
    void updateBalance(Balance balance) {
        Utils.writeToCsvFile(configuration.projections.balances, balance)
    }
}
