package memento.samples.bank.report.application.port.out

import memento.samples.bank.report.domain.Account
import memento.samples.bank.report.domain.Balance

import java.time.OffsetDateTime

class ReportPorts {
    interface ListAccountPort {
        List<Account> list()
    }

    interface LoadBalancePort {
        Optional<Balance> loadBalance(UUID accountId)
    }

    interface ListBalancePort {
        List<Balance> listBalanceHistory(UUID accountId, OffsetDateTime from)
    }
}
