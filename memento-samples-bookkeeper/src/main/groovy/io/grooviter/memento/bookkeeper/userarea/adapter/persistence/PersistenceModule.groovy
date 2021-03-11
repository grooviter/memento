package io.grooviter.memento.bookkeeper.userarea.adapter.persistence

import com.google.inject.AbstractModule
import com.google.inject.Provides
import io.grooviter.memento.bookkeeper.userarea.application.port.out.UserAreaPorts

class PersistenceModule extends AbstractModule {
    void configure() {
        bind(UserAreaPorts.ListAccountsPort).to(UserAreaPersistenceAdapter)
    }

    @Provides
    UserAreaPersistenceAdapter getPersistenceAdapter() {
        return new UserAreaPersistenceAdapter()
    }
}
