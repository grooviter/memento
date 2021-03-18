package io.grooviter.memento.bank.report.adapter.console

import groovy.transform.CompileStatic
import io.grooviter.memento.bank.infra.console.AllCommands
import io.grooviter.memento.bank.infra.console.Utils
import io.grooviter.memento.bank.report.application.port.in.UseCases
import io.grooviter.memento.bank.report.domain.Balance
import picocli.CommandLine

import javax.inject.Inject

@CompileStatic
@CommandLine.Command(version = "1.0", name = "account-balance", description = "shows a given account balance")
class ShowBalanceCommand implements Runnable {
    @CommandLine.ParentCommand
    AllCommands parent

    @CommandLine.Option(names = ['-i', '--account'], description = "account ID", required = true)
    UUID accountId

    @Inject
    UseCases.ShowAccountBalanceQuery showAccountBalanceQuery

    @Override
    void run() {
        UseCases.ShowAccountBalanceQuery.Params params = UseCases.ShowAccountBalanceQuery.Params.builder()
            .accountId(accountId)
            .build()

        showAccountBalanceQuery
            .showBalance(params)
            .map(ShowBalanceCommand::renderBalance)
            .ifPresentOrElse(
                message -> parent.printlnAnsi(message),
                () -> parent.printlnAnsi("@|yellow NO BALANCE FOUND |@"))
    }

    private static String renderBalance(Balance balance) {
        return Utils.tableBuilder(Balance)
            .columnNames('DATE', 'BALANCE')
            .addColumnConsumer(blc -> blc.createdAt)
            .addColumnConsumer(blc -> blc.balance)
            .setTableWidth(60)
            .rows([balance])
            .build()
    }
}
