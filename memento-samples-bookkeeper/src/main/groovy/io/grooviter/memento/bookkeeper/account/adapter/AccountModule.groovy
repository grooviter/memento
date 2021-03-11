package io.grooviter.memento.bookkeeper.account.adapter

import com.google.inject.AbstractModule
import io.grooviter.memento.bookkeeper.account.adapter.console.ConsoleModule
import io.grooviter.memento.bookkeeper.account.adapter.persistence.PersistenceModule
import io.grooviter.memento.bookkeeper.account.adapter.services.ServicesModule

class AccountModule extends AbstractModule {
    void configure() {
        install(new ConsoleModule())
        install(new PersistenceModule())
        install(new ServicesModule())
    }
}
