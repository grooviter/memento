package io.grooviter.memento.bank.report.application.services

import io.grooviter.memento.bank.report.application.port.in.UseCases
import io.grooviter.memento.bank.report.application.port.out.ReportPorts
import io.grooviter.memento.bank.report.domain.Account

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class ListAccountsService implements UseCases.ListAccountsQuery {

    @Inject
    ReportPorts.ListAccountPort listAccountPort

    @Override
    List<Account> list() {
        return listAccountPort.list()
    }
}
