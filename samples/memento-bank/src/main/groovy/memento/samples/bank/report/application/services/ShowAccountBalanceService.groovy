package memento.samples.bank.report.application.services

import memento.samples.bank.report.application.port.in.UseCases
import memento.samples.bank.report.application.port.out.ReportPorts
import memento.samples.bank.report.domain.Balance

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class ShowAccountBalanceService implements UseCases.ShowAccountBalanceQuery {

    @Inject
    ReportPorts.LoadBalancePort loadBalancePort

    @Override
    Optional<Balance> showBalance(Params params) {
        return loadBalancePort.loadBalance(params.accountId)
    }
}
