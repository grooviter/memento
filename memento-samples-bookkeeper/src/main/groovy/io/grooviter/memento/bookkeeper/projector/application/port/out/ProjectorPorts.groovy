package io.grooviter.memento.bookkeeper.projector.application.port.out

import io.grooviter.memento.bookkeeper.projector.domain.Account
import io.grooviter.memento.bookkeeper.projector.domain.Balance

class ProjectorPorts {
    interface SaveAccountPort {
        void save(Account account)
    }

    interface UpdateBalancePort {
        void updateBalance(Balance balance)
    }
}
