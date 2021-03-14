package io.grooviter.memento.bookkeeper.report.application.services

import io.grooviter.memento.bookkeeper.report.application.port.in.UseCases
import io.grooviter.memento.bookkeeper.report.application.port.out.ReportPorts
import io.grooviter.memento.bookkeeper.report.domain.Balance

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShowAccountBalanceService implements UseCases.ShowAccountBalanceQuery {

    @Inject
    ReportPorts.LoadBalancePort loadBalancePort

    @Override
    Optional<Balance> showBalance(Params params) {
        return loadBalancePort.loadBalance(params.accountId)
    }
}
