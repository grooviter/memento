package io.grooviter.memento.bookkeeper.projector.adapter.persistence

import io.grooviter.memento.bookkeeper.projector.application.port.out.ProjectorPorts
import io.grooviter.memento.bookkeeper.projector.domain.AccountCreated

class ProjectorPersistenceAdapter implements ProjectorPorts.SaveAccountCreatedPort {
    @Override
    void save(AccountCreated accountCreated) {

    }
}
