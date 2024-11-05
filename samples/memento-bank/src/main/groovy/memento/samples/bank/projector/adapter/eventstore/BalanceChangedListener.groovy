package memento.samples.bank.projector.adapter.eventstore

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import memento.samples.bank.infra.eventstore.TopicAwareEvent
import memento.samples.bank.projector.application.port.UseCases
import memento.samples.bank.projector.domain.events.Deposit
import memento.samples.bank.projector.domain.events.Withdrawal
import io.micronaut.runtime.event.annotation.EventListener

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Slf4j
@Singleton
class BalanceChangedListener {

    @Inject
    ObjectMapper objectMapper

    @Inject
    UseCases.UpdateAccountBalance updateAccountBalance

    @EventListener
    void onBalanceChanged(TopicAwareEvent event) {
        resolveEventBean(event).ifPresent(updateAccountBalance::store)
    }

    Optional<UseCases.UpdateAccountBalance.Params> resolveEventBean(TopicAwareEvent event) {
        switch (event.topic){
            case 'DEPOSIT_MADE': return resolveBalanceFromDeposit(event)
            case 'WITHDRAWAL_MADE': return resolveBalanceFromWithdrawal(event)
            default:
            return Optional.empty()
        }
    }

    private Optional<UseCases.UpdateAccountBalance.Params> resolveBalanceFromDeposit(TopicAwareEvent event) {
        Deposit deposit = objectMapper.readerFor(Deposit).readValue(event.json)

        UseCases.UpdateAccountBalance.Params params = UseCases.UpdateAccountBalance.Params.builder()
            .id(deposit.accountId)
            .newBalance(deposit.newBalance)
            .createdAt(deposit.createdAt)
            .build()

        return Optional.of(params)
    }

    private Optional<UseCases.UpdateAccountBalance.Params> resolveBalanceFromWithdrawal(TopicAwareEvent event) {
        Withdrawal withdrawal = objectMapper.readerFor(Withdrawal).readValue(event.json)

        UseCases.UpdateAccountBalance.Params params = UseCases.UpdateAccountBalance.Params.builder()
            .id(withdrawal.accountId)
            .newBalance(withdrawal.newBalance)
            .createdAt(withdrawal.createdAt)
            .build()

        return Optional.of(params)
    }
}
