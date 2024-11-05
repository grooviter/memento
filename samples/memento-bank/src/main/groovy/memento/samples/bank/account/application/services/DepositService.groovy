package memento.samples.bank.account.application.services

import memento.samples.bank.account.application.port.in.UseCases
import memento.samples.bank.account.application.port.out.AccountPorts

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class DepositService implements UseCases.DepositCommand {

    @Inject
    AccountPorts.LoadAccountPort loadAccountPort

    @Inject
    AccountPorts.SaveAccountPort saveAccountPort

    @Override
    void deposit(Params params) {
        loadAccountPort
            .load(params.accountId)
            .map(account -> account.deposit(params.amount))
            .ifPresent(saveAccountPort::save)
    }
}
