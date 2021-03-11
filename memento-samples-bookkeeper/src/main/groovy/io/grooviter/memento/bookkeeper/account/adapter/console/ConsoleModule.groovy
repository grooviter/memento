package io.grooviter.memento.bookkeeper.account.adapter.console

import com.google.inject.AbstractModule
import io.grooviter.memento.bookkeeper.account.adapter.console.commands.DepositCommand
import io.grooviter.memento.bookkeeper.account.adapter.console.commands.CreateCommand
import io.grooviter.memento.bookkeeper.infra.console.AllCommands

class ConsoleModule extends AbstractModule {
    void configure() {
        bind(AllCommands)
        bind(CreateCommand)
        bind(DepositCommand)
    }
}
