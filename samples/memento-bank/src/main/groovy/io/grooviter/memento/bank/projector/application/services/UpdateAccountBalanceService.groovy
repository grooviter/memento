package io.grooviter.memento.bank.projector.application.services

import io.grooviter.memento.bank.projector.application.port.in.UseCases
import io.grooviter.memento.bank.projector.application.port.out.ProjectorPorts
import io.grooviter.memento.bank.projector.domain.Balance

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateAccountBalanceService implements UseCases.UpdateAccountBalance {
    @Inject
    ProjectorPorts.UpdateBalancePort updateBalancePort

    @Override
    void store(Params params) {
        Balance balance = Balance.builder()
            .accountId(params.id)
            .currentBalance(params.newBalance)
            .createdAt(params.createdAt)
            .build()

        updateBalancePort.updateBalance(balance)
    }
}
