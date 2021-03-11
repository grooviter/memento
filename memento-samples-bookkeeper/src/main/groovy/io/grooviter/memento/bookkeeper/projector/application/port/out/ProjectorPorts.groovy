package io.grooviter.memento.bookkeeper.projector.application.port.out

import io.grooviter.memento.bookkeeper.projector.domain.Account

class ProjectorPorts {
    interface LoadAccountPort {
        Optional<Account> load(UUID id)
    }

    interface SaveAccountPort {
        void save(Account account)
    }
}
