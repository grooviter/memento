package memento.samples.bank.account.adapter.console.commands

import memento.samples.bank.account.application.port.UseCases
import memento.samples.bank.infra.console.AllCommands
import picocli.CommandLine

import jakarta.inject.Inject

@CommandLine.Command(
    version = "1.0",
    name = "account-deposit",
    description = "adds a new deposit to a given account"
)
class DepositCommand implements Runnable {
    @CommandLine.ParentCommand
    AllCommands parent

    @CommandLine.Option(names = ['-i', '--account'], description = "account ID", required = true)
    UUID account

    @CommandLine.Option(names = ['-a', '--amount'], description = "amount deposited", required = true)
    BigDecimal amount

    @Inject
    UseCases.DepositCommand depositCommand

    @Override
    void run() {
        UseCases.DepositCommand.Params params = UseCases.DepositCommand.Params.builder()
            .accountId(account)
            .amount(amount)
            .build()

        depositCommand.deposit(params)
        parent.printlnAnsi("amount deposited: @|green $amount |@")
    }
}
