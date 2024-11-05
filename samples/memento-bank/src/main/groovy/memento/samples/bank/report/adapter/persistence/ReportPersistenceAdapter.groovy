package memento.samples.bank.report.adapter.persistence

import memento.samples.bank.infra.Configuration
import memento.samples.bank.infra.persistence.Utils
import memento.samples.bank.report.application.port.ReportPorts
import memento.samples.bank.report.domain.Account
import memento.samples.bank.report.domain.Balance

import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.time.OffsetDateTime
import java.util.function.Predicate
import java.util.stream.Collectors

@Singleton
class ReportPersistenceAdapter implements
    ReportPorts.ListAccountPort,
    ReportPorts.LoadBalancePort,
    ReportPorts.ListBalancePort {

    @Inject
    Configuration configuration

    @Override
    List<Account> list() {
        return Utils
            .createStreamForFile(configuration.projections.accounts, Account)
            .collect(Collectors.toList())
    }

    @Override
    Optional<Balance> loadBalance(UUID accountId) {
        return Utils
            .createStreamForFile(configuration.projections.balances, Balance)
            .filter(idEquals(accountId))
            .sorted(byDateDesc())
            .findFirst()
    }

    @Override
    List<Balance> listBalanceHistory(UUID accountId, OffsetDateTime from) {
        return Utils
            .createStreamForFile(configuration.projections.balances, Balance)
            .filter(idEquals(accountId))
            .sorted(byDateDesc())
            .filter(afterDate(from))
            .collect(Collectors.toList())
    }

    private static Predicate<Balance> afterDate(OffsetDateTime from) {
        return (balance) -> Optional
            .ofNullable(from)
            .map(date -> balance.createdAt.isAfter(date))
            .orElse(true)
    }

    private static Predicate<Balance> idEquals(UUID id) {
        return (balance) -> balance.accountId == id.toString()
    }

    private static Comparator<Balance> byDateDesc() {
        return (left, right) -> left.createdAt.isAfter(right.createdAt) ? -1 : 1
    }
}
