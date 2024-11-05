package memento.samples.bank.report.application.services

import memento.samples.bank.report.application.port.in.UseCases
import memento.samples.bank.report.application.port.out.ReportPorts
import memento.samples.bank.report.domain.Balance
import jakarta.inject.Inject
import jakarta.inject.Singleton


@Singleton
class ShowAccountBalanceHistoryService implements UseCases.ShowAccountBalanceHistoryQuery {

    @Inject
    ReportPorts.ListBalancePort listBalancePort

    @Override
    List<Balance> showBalanceHistory(Params params) {
        return listBalancePort.listBalanceHistory(params.accountId, params.from)
    }
}
