package io.grooviter.memento.bank.account.adapter.console.commands

import io.grooviter.memento.bank.account.application.port.in.UseCases
import io.grooviter.memento.bank.infra.console.AllCommands
import picocli.CommandLine

import javax.inject.Inject

@CommandLine.Command(
    version = "1.0",
    name = "account-create",
    description = "creates a new account in the system"
)
class CreateCommand implements Runnable {
    @CommandLine.ParentCommand
    AllCommands parent

    @CommandLine.Option(names = ['-n', '--name'], description = "holder's name", required = true)
    String name

    @CommandLine.Option(names = ['-i', '--iban'], description = 'IBAN number', required = true)
    String iban

    @Inject
    UseCases.CreateAccountCommand createAccountCommand

    @Override
    void run() {
        UseCases.CreateAccountCommand.Params params = UseCases.CreateAccountCommand.Params.builder()
            .iban(iban)
            .holderName(name)
            .build()

        UUID accountId = createAccountCommand.create(params)
        parent.printlnAnsi("account created ID: @|green $accountId |@")
    }
}
