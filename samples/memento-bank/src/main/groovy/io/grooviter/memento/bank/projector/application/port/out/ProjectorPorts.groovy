package io.grooviter.memento.bank.projector.application.port.out

import io.grooviter.memento.bank.projector.domain.Account
import io.grooviter.memento.bank.projector.domain.Balance

class ProjectorPorts {
    interface SaveAccountPort {
        void save(Account account)
    }

    interface UpdateBalancePort {
        void updateBalance(Balance balance)
    }
}
