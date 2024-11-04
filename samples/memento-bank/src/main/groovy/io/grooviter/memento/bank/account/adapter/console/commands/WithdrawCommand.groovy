package io.grooviter.memento.bank.account.adapter.console.commands

import io.grooviter.memento.bank.account.application.port.in.UseCases
import io.grooviter.memento.bank.infra.console.AllCommands
import picocli.CommandLine

import jakarta.inject.Inject

@CommandLine.Command(
    version = '1.0',
    name = 'account-withdraw',
    description = 'adds a withdraw transaction to the account'
)
class WithdrawCommand implements Runnable {
    @CommandLine.ParentCommand
    AllCommands parent

    @CommandLine.Option(names = ['-i', '--account'], description = "account ID", required = true)
    UUID account

    @CommandLine.Option(names = ['-a', '--amount'], description = "amount deposited", required = true)
    BigDecimal amount

    @Inject
    UseCases.WithdrawCommand withdrawCommand

    @Override
    void run() {
        UseCases.WithdrawCommand.Params params = UseCases.WithdrawCommand.Params.builder()
            .accountId(account)
            .amount(amount)
            .build()

        try {
            withdrawCommand.withdraw(params)
            parent.printlnAnsi("amount withdrawn: @|green $amount |@")
        } catch (Throwable ignored) {
            parent.printlnAnsi("@|red Not enough founds in order to withdraw |@ $amount")
        }
    }
}
