package io.grooviter.memento.bank.account.application.services

import io.grooviter.memento.bank.account.application.port.in.UseCases
import io.grooviter.memento.bank.account.application.port.out.AccountPorts
import io.grooviter.memento.bank.account.domain.Account

import javax.inject.Inject
import javax.inject.Singleton

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
