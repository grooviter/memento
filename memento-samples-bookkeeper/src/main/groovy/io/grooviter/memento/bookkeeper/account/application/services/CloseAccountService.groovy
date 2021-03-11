package io.grooviter.memento.bookkeeper.account.application.services

import io.grooviter.memento.bookkeeper.account.application.port.in.UseCases
import io.grooviter.memento.bookkeeper.account.application.port.out.AccountPorts
import io.grooviter.memento.bookkeeper.account.domain.Account

class CloseAccountService implements UseCases.CloseAccountCommand {

    AccountPorts.LoadAccountPort loadAccountPort

    AccountPorts.SaveAccountPort saveAccountPort

    @Override
    void close(Params params) {
        loadAccountPort.load(params.accountId)
            .map(Account::close)
            .ifPresent(saveAccountPort::save)
    }
}
