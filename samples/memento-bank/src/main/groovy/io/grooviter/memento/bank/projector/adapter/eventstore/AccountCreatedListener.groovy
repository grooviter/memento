package io.grooviter.memento.bank.projector.adapter.eventstore

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import io.grooviter.memento.bank.infra.eventstore.TopicAwareEvent
import io.grooviter.memento.bank.projector.application.port.in.UseCases
import io.grooviter.memento.bank.projector.domain.Account
import io.micronaut.runtime.event.annotation.EventListener

import javax.inject.Inject
import javax.inject.Singleton

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
