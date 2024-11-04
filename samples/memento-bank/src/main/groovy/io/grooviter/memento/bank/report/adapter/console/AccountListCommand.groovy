package io.grooviter.memento.bank.report.adapter.console

import groovy.transform.CompileStatic
import io.grooviter.memento.bank.infra.console.AllCommands
import io.grooviter.memento.bank.infra.console.Utils
import io.grooviter.memento.bank.report.application.port.in.UseCases
import io.grooviter.memento.bank.report.domain.Account
import picocli.CommandLine

import jakarta.inject.Inject

@CompileStatic
@CommandLine.Command(version = "1.0", name = "account-list", description = "lists all available accounts")
class AccountListCommand implements Runnable {
    @CommandLine.ParentCommand
    AllCommands parent

    @Inject
    UseCases.ListAccountsQuery listAccountsQuery

    @Override
    void run() {
        List<Account> accountList = listAccountsQuery.list()

        if (!accountList) {
            parent.printlnAnsi("@|yellow NO ACCOUNTS FOUND |@")
            return
        }

        parent.printlnAnsi(createTable(accountList))
    }

    private static String createTable(List<Account> accountList) {
        return Utils.tableBuilder(Account)
            .columnNames('ID', 'HOLDER NAME', 'CREATED_AT')
            .addColumnConsumer((account) -> account.id)
            .addColumnConsumer((account) -> account.name)
            .addColumnConsumer((account) -> account.createdAt)
            .rows(accountList)
            .build()
    }
}
