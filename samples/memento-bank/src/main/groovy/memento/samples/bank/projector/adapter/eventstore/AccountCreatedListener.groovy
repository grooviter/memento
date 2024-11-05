package memento.samples.bank.projector.adapter.eventstore

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import memento.samples.bank.infra.eventstore.TopicAwareEvent
import memento.samples.bank.projector.application.port.UseCases
import memento.samples.bank.projector.domain.Account
import io.micronaut.runtime.event.annotation.EventListener

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Slf4j
@Singleton
class AccountCreatedListener {

    @Inject
    UseCases.StoreNewAccount storeNewAccount

    @Inject
    ObjectMapper objectMapper

    @EventListener
    void onAccountCreated(TopicAwareEvent event) {
        if (event.topic != 'ACCOUNT_CREATED') return

        log.debug("materializing new account")
        Account account = objectMapper.readerFor(Account).readValue(event.json)

        UseCases.StoreNewAccount.Params params = UseCases.StoreNewAccount.Params.builder()
            .id(UUID.fromString(account.id))
            .holderName(account.name)
            .createdAt(account.createdAt)
            .build()

        storeNewAccount.store(params)
    }
}
