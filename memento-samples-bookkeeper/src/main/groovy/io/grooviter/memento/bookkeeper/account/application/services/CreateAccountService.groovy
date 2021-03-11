package io.grooviter.memento.bookkeeper.account.application.services

import io.grooviter.memento.bookkeeper.account.application.port.in.UseCases
import io.grooviter.memento.bookkeeper.account.application.port.out.AccountPorts
import io.grooviter.memento.bookkeeper.account.domain.Account

class CreateAccountService implements UseCases.CreateAccountCommand {

    AccountPorts.SaveAccountPort saveAccountPort

    @Override
    UUID create(Params params) {
        Account account = Account.create(params.iban, params.holderName)
        saveAccountPort.save(account)

        return account.id
    }
}
