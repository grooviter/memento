package io.grooviter.memento.bookkeeper.infra.console

import io.grooviter.memento.bookkeeper.account.adapter.console.commands.CloseCommand
import io.grooviter.memento.bookkeeper.account.adapter.console.commands.DepositCommand
import io.grooviter.memento.bookkeeper.account.adapter.console.commands.CreateCommand
import io.grooviter.memento.bookkeeper.account.adapter.console.commands.WithdrawCommand
import io.grooviter.memento.bookkeeper.userarea.adapter.console.ListAccountsCommand
import picocli.CommandLine
import picocli.CommandLine.Command

@Command(subcommands = [
    CreateCommand,
    DepositCommand,
    WithdrawCommand,
    CloseCommand,
    //ListAccountsCommand
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

    private String toAnsi(String text) {
        return spec.commandLine().getColorScheme().ansi().string(text)
    }
}