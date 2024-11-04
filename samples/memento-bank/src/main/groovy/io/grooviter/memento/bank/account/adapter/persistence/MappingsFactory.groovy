package io.grooviter.memento.bank.account.adapter.persistence

import io.grooviter.memento.bank.account.domain.Account
import io.grooviter.memento.bank.account.domain.events.*
import io.grooviter.memento.model.Mappings
import io.micronaut.context.annotation.Factory

import jakarta.inject.Singleton

@Factory
class MappingsFactory {
    @Singleton
    Mappings providesMappings() {
        return Mappings.builder()
            .addMapping("ACCOUNT", Account)
            .addMapping("ACCOUNT_CREATED", Created)
            .addMapping("ACCOUNT_CLOSED", Closed)
            .addMapping("DEPOSIT_MADE", DepositMade)
            .addMapping("WITHDRAWAL_MADE", WithdrawalMade)
            .addMapping("COMMISSION", CommissionApplied)
            .build()
    }
}
