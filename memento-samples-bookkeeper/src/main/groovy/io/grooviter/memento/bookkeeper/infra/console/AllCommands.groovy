package io.grooviter.memento.bookkeeper.infra.console

import io.grooviter.memento.bookkeeper.account.adapter.console.commands.CloseCommand
import io.grooviter.memento.bookkeeper.account.adapter.console.commands.CreateCommand
import io.grooviter.memento.bookkeeper.account.adapter.console.commands.DepositCommand
import io.grooviter.memento.bookkeeper.account.adapter.console.commands.WithdrawCommand
import io.grooviter.memento.bookkeeper.report.adapter.console.AccountListCommand
import io.grooviter.memento.bookkeeper.report.adapter.console.ShowBalanceCommand
import io.grooviter.memento.bookkeeper.report.adapter.console.ShowBalanceHistoryCommand
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