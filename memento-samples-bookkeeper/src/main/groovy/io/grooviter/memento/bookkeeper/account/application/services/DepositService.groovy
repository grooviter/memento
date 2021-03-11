package io.grooviter.memento.bookkeeper.account.application.services

import io.grooviter.memento.bookkeeper.account.application.port.in.UseCases
import io.grooviter.memento.bookkeeper.account.application.port.out.AccountPorts

class DepositService implements UseCases.DepositCommand {

    AccountPorts.LoadAccountPort loadAccountPort

    AccountPorts.SaveAccountPort saveAccountPort

    @Override
    void deposit(Params params) {
        loadAccountPort
            .load(params.accountId)
            .map(account -> account.deposit(params.amount))
            .ifPresent(saveAccountPort::save)
    }
}
