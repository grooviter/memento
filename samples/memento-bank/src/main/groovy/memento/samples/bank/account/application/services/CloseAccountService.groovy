package memento.samples.bank.account.application.services

import memento.samples.bank.account.application.port.UseCases
import memento.samples.bank.account.application.port.AccountPorts
import memento.samples.bank.account.domain.Account

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class CloseAccountService implements UseCases.CloseAccountCommand {

    @Inject
    AccountPorts.LoadAccountPort loadAccountPort

    @Inject
    AccountPorts.SaveAccountPort saveAccountPort

    @Override
    void close(Params params) {
        loadAccountPort.load(params.accountId)
            .map(Account::close)
            .ifPresent(saveAccountPort::save)
    }
}
