package io.grooviter.memento.bookkeeper.report.application.services

import io.grooviter.memento.bookkeeper.report.application.port.in.UseCases
import io.grooviter.memento.bookkeeper.report.application.port.out.ReportPorts
import io.grooviter.memento.bookkeeper.report.domain.Balance

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShowAccountBalanceHistoryService implements UseCases.ShowAccountBalanceHistoryQuery {

    @Inject
    ReportPorts.ListBalancePort listBalancePort

    @Override
    List<Balance> showBalanceHistory(Params params) {
        return listBalancePort.listBalanceHistory(params.accountId, params.from)
    }
}
