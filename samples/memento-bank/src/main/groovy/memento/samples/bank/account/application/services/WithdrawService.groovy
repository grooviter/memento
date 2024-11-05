package memento.samples.bank.account.application.services

import memento.samples.bank.account.application.port.in.UseCases
import memento.samples.bank.account.application.port.out.AccountPorts

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class WithdrawService implements UseCases.WithdrawCommand {

    @Inject
    AccountPorts.LoadAccountPort loadAccountPort

    @Inject
    AccountPorts.SaveAccountPort saveAccountPort

    @Override
    void withdraw(Params params) {
        loadAccountPort.load(params.accountId)
            .map(acc -> acc.withdrawal(params.amount))
            .ifPresent(saveAccountPort::save)
    }
}
