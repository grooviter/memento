package memento.samples.bank.account.adapter.console.commands

import memento.samples.bank.account.application.port.in.UseCases
import memento.samples.bank.infra.console.AllCommands
import picocli.CommandLine

import jakarta.inject.Inject

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
