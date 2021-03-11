package io.grooviter.memento.bookkeeper.projector.application.port.out

import io.grooviter.memento.bookkeeper.projector.domain.AccountCreated

final class ProjectorPorts {

    interface SaveAccountCreatedPort {
        void save(AccountCreated accountCreated)
    }
}
