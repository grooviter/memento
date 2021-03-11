package io.grooviter.memento.bookkeeper.account.adapter.console.commands

import io.grooviter.memento.bookkeeper.infra.console.AllCommands
import io.grooviter.memento.bookkeeper.account.application.port.in.UseCases
import picocli.CommandLine

import javax.inject.Inject

@CommandLine.Command(
    version = '1.0',
    name = 'account-close',
    description = 'closes a given account'
)
class CloseCommand implements Runnable {
    @CommandLine.ParentCommand
    AllCommands parent

    @CommandLine.Option(names = ['-i', '--account'], description = "account ID", required = true)
    UUID account

    @Inject
    UseCases.CloseAccountCommand closeAccountCommand

    @Override
    void run() {
        UseCases.CloseAccountCommand.Params params = UseCases.CloseAccountCommand.Params.builder()
            .accountId(account)
            .build()

        try {
            closeAccountCommand.close(params)
            parent.printlnAnsi("@|green account successfully closed! |@")
        } catch (Throwable ignored) {
            parent.printlnAnsi("@|red check there's still some balance left |@")
        }
    }
}
