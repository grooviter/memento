package io.grooviter.memento.bookkeeper.account.application.services

import io.grooviter.memento.bookkeeper.account.application.port.in.UseCases
import io.grooviter.memento.bookkeeper.account.application.port.out.AccountPorts

class WithdrawService implements UseCases.WithdrawCommand {

    AccountPorts.LoadAccountPort loadAccountPort

    AccountPorts.SaveAccountPort saveAccountPort

    @Override
    void withdraw(Params params) {
        loadAccountPort.load(params.accountId)
            .map(acc -> acc.withdrawal(params.amount))
            .ifPresent(saveAccountPort::save)
    }
}
