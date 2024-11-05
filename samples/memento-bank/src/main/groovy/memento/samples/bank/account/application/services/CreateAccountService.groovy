package memento.samples.bank.account.application.services

import memento.samples.bank.account.application.port.UseCases
import memento.samples.bank.account.application.port.AccountPorts
import memento.samples.bank.account.domain.Account

import jakarta.inject.Inject
import jakarta.inject.Singleton

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
