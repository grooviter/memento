package memento.samples.bank.projector.adapter.persistence

import memento.samples.bank.infra.Configuration
import memento.samples.bank.infra.persistence.Utils
import memento.samples.bank.projector.application.port.ProjectorPorts
import memento.samples.bank.projector.domain.Account
import memento.samples.bank.projector.domain.Balance

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
