package memento.samples.bank.projector.application.port

import memento.samples.bank.projector.domain.Account
import memento.samples.bank.projector.domain.Balance

class ProjectorPorts {
    interface SaveAccountPort {
        void save(Account account)
    }

    interface UpdateBalancePort {
        void updateBalance(Balance balance)
    }
}
