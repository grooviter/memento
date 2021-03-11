package io.grooviter.memento.bookkeeper

import com.google.inject.Guice
import com.google.inject.Injector
import io.grooviter.memento.bookkeeper.account.adapter.AccountModule
import io.grooviter.memento.bookkeeper.infra.InfraModule
import io.grooviter.memento.bookkeeper.infra.console.BookKeeperRunner
import io.grooviter.memento.bookkeeper.projector.ProjectorModule
import io.grooviter.memento.bookkeeper.userarea.UserAreaModule

class Application {
    static void main(String[] args) throws Exception {
        Injector dependencyInjection = Guice.createInjector(
            new InfraModule(),
            new AccountModule(),
            new UserAreaModule(),
            new ProjectorModule()
        )

        BookKeeperRunner
            .create(dependencyInjection)
            .run()
    }
}
