package io.grooviter.memento.bookkeeper.account.adapter.persistence

import io.grooviter.memento.bookkeeper.account.domain.Account
import io.grooviter.memento.bookkeeper.account.domain.events.*
import io.grooviter.memento.model.Mappings
import io.micronaut.context.annotation.Factory

import javax.inject.Singleton

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
