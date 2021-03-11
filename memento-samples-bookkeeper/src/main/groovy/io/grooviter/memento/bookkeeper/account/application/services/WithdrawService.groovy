package io.grooviter.memento.bookkeeper.account.application.services

import io.grooviter.memento.bookkeeper.account.application.port.in.UseCases
import io.grooviter.memento.bookkeeper.account.application.port.out.AccountPorts

import javax.inject.Inject
import javax.inject.Singleton

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
