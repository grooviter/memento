package io.grooviter.memento.bookkeeper.report.application.port.out

import io.grooviter.memento.bookkeeper.report.domain.Account
import io.grooviter.memento.bookkeeper.report.domain.Balance

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
