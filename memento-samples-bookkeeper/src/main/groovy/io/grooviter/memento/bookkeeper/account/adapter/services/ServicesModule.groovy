package io.grooviter.memento.bookkeeper.account.adapter.services

import com.google.inject.AbstractModule
import com.google.inject.Provides
import io.grooviter.memento.bookkeeper.account.application.port.in.UseCases
import io.grooviter.memento.bookkeeper.account.application.port.out.AccountPorts
import io.grooviter.memento.bookkeeper.account.application.services.CloseAccountService
import io.grooviter.memento.bookkeeper.account.application.services.CreateAccountService
import io.grooviter.memento.bookkeeper.account.application.services.DepositService
import io.grooviter.memento.bookkeeper.account.application.services.WithdrawService

class ServicesModule extends AbstractModule {
    @Provides
    UseCases.CreateAccountCommand getCreateAccountService(AccountPorts.SaveAccountPort saveAccountPort) {
        return new CreateAccountService(saveAccountPort: saveAccountPort)
    }

    @Provides
    UseCases.DepositCommand getDepositService(
        AccountPorts.LoadAccountPort loadAccountPort,
        AccountPorts.SaveAccountPort saveAccountPort
    ) {
        return new DepositService(loadAccountPort: loadAccountPort, saveAccountPort: saveAccountPort)
    }

    @Provides
    UseCases.WithdrawCommand getWithdrawalService(
        AccountPorts.LoadAccountPort loadAccountPort,
        AccountPorts.SaveAccountPort saveAccountPort
    ) {
        return new WithdrawService(loadAccountPort: loadAccountPort, saveAccountPort: saveAccountPort)
    }

    @Provides
    UseCases.CloseAccountCommand closeAccountCommand(
        AccountPorts.LoadAccountPort loadAccountPort,
        AccountPorts.SaveAccountPort saveAccountPort
    ) {
        return new CloseAccountService(loadAccountPort: loadAccountPort, saveAccountPort: saveAccountPort)
    }
}
