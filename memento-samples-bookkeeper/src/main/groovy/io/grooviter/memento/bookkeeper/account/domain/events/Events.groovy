package io.grooviter.memento.bookkeeper.account.domain.events

final class Events {

    static Created created(String iban, String name, Integer version) {
        return Created.builder().iban(iban).name(name).version(version).build()
    }

    static Closed close(Integer version) {
        return Closed.builder().version(version).build()
    }

    static DepositMade depositMade(BigDecimal amount, BigDecimal newBalance, Integer version) {
        return DepositMade.builder()
            .amount(amount)
            .balance(newBalance)
            .version(version)
            .build()
    }

    static WithdrawalMade withdrawal(BigDecimal amount, BigDecimal newBalance, Integer version) {
        return WithdrawalMade.builder()
            .amount(amount)
            .balance(newBalance)
            .version(version)
            .build()
    }

    static CommissionApplied commission(BigDecimal amount, BigDecimal newBalance, Integer version) {
        return CommissionApplied.builder()
            .amount(amount)
            .balance(newBalance)
            .version(version)
            .build()
    }
}
