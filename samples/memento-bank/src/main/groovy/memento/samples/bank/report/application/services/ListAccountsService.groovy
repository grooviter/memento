package memento.samples.bank.report.application.services

import memento.samples.bank.report.application.port.UseCases
import memento.samples.bank.report.application.port.ReportPorts
import memento.samples.bank.report.domain.Account

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
