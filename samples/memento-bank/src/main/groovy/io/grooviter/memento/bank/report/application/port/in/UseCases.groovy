package io.grooviter.memento.bank.report.application.port.in

import groovy.transform.builder.Builder
import io.grooviter.memento.bank.report.domain.Account
import io.grooviter.memento.bank.report.domain.Balance

import java.time.OffsetDateTime

class UseCases {
    interface ListAccountsQuery {
        List<Account> list()
    }

    interface ShowAccountBalanceQuery {
        @Builder
        class Params {
            UUID accountId
        }
        Optional<Balance> showBalance(Params params)
    }

    interface ShowAccountBalanceHistoryQuery {
        @Builder
        class Params {
            UUID accountId
            OffsetDateTime from
        }
        List<Balance> showBalanceHistory(Params params)
    }
}
