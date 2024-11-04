package io.grooviter.memento.bank.account.domain.events

final class Events {

    static Created created(String iban, String name) {
        return Created.builder().iban(iban).name(name).build()
    }

    static Closed closed() {
        return Closed.builder().build()
    }

    static DepositMade depositMade(BigDecimal amount, BigDecimal newBalance) {
        return DepositMade.builder()
            .amount(amount)
            .balance(newBalance)
            .build()
    }

    static WithdrawalMade withdrawal(BigDecimal amount, BigDecimal newBalance) {
        return WithdrawalMade.builder()
            .amount(amount)
            .balance(newBalance)
            .build()
    }

    static CommissionApplied commissionAdded(BigDecimal amount, BigDecimal newBalance) {
        return CommissionApplied.builder()
            .amount(amount)
            .balance(newBalance)
            .build()
    }
}
