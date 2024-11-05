package memento.samples.bank.report.adapter.console

import groovy.transform.CompileStatic
import memento.samples.bank.infra.console.AllCommands
import memento.samples.bank.infra.console.Utils
import memento.samples.bank.report.application.port.UseCases
import memento.samples.bank.report.domain.Balance
import picocli.CommandLine

import jakarta.inject.Inject
import java.time.OffsetDateTime

@CompileStatic
@CommandLine.Command(
    version = "1.0",
    name = "account-balance-history",
    description = "shows a given account balance history"
)
class ShowBalanceHistoryCommand implements Runnable {
    @CommandLine.ParentCommand
    AllCommands parent

    @CommandLine.Option(names = ['-i', '--account'], description = "account ID", required = true)
    UUID accountId

    @CommandLine.Option(names = ['-f', '--from'], description = "from", required = false)
    OffsetDateTime from

    @Inject
    UseCases.ShowAccountBalanceHistoryQuery showAccountBalanceHistoryQuery

    @Override
    void run() {
        UseCases.ShowAccountBalanceHistoryQuery.Params params = UseCases.ShowAccountBalanceHistoryQuery.Params.builder()
            .accountId(accountId)
            .from(from)
            .build()

        parent.printlnAnsi(
            renderTable(
                showAccountBalanceHistoryQuery.showBalanceHistory(params)
            )
        )
    }

    private static String renderTable(List<Balance> balanceList) {
        return Utils.tableBuilder(Balance)
            .columnNames('BALANCE', 'CREATED AT')
            .addColumnConsumer(blc -> blc.balance)
            .addColumnConsumer(blc -> blc.createdAt)
            .setTableWidth(60)
            .rows(balanceList)
            .build()
    }
}
