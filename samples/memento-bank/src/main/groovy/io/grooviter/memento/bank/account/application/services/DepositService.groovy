package io.grooviter.memento.bank.account.application.services

import io.grooviter.memento.bank.account.application.port.in.UseCases
import io.grooviter.memento.bank.account.application.port.out.AccountPorts

import javax.inject.Inject
import javax.inject.Singleton

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
