package memento.samples.bank.projector.application.services

import memento.samples.bank.projector.application.port.in.UseCases
import memento.samples.bank.projector.application.port.out.ProjectorPorts
import memento.samples.bank.projector.domain.Balance

import jakarta.inject.Inject
import jakarta.inject.Singleton

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
