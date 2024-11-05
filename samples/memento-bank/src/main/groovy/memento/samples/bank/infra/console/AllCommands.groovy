package memento.samples.bank.infra.console

import memento.samples.bank.account.adapter.console.commands.CloseCommand
import memento.samples.bank.account.adapter.console.commands.CreateCommand
import memento.samples.bank.account.adapter.console.commands.DepositCommand
import memento.samples.bank.account.adapter.console.commands.WithdrawCommand
import memento.samples.bank.report.adapter.console.AccountListCommand
import memento.samples.bank.report.adapter.console.ShowBalanceCommand
import memento.samples.bank.report.adapter.console.ShowBalanceHistoryCommand
import picocli.CommandLine
import picocli.CommandLine.Command

@Command(subcommands = [
    CreateCommand,
    DepositCommand,
    WithdrawCommand,
    CloseCommand,
    AccountListCommand,
    ShowBalanceCommand,
    ShowBalanceHistoryCommand
])
class AllCommands implements Runnable {

    @CommandLine.Spec
    CommandLine.Model.CommandSpec spec

    PrintWriter out

    @Override
    void run() { /* EMPTY */ }

    void printlnAnsi(String text) {
        out.println(toAnsi(text))
    }

    String toAnsi(String text) {
        return spec.commandLine().getColorScheme().ansi().string(text)
    }
}