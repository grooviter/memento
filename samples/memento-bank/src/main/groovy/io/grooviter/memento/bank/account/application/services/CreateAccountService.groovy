package io.grooviter.memento.bank.account.application.services

import io.grooviter.memento.bank.account.application.port.in.UseCases
import io.grooviter.memento.bank.account.application.port.out.AccountPorts
import io.grooviter.memento.bank.account.domain.Account

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateAccountService implements UseCases.CreateAccountCommand {

    @Inject
    AccountPorts.SaveAccountPort saveAccountPort

    @Override
    UUID create(Params params) {
        Account account = Account.create(params.iban, params.holderName)
        saveAccountPort.save(account)

        return account.id
    }
}
