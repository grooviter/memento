package io.grooviter.memento.bookkeeper.projector.adapter.eventstore

import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j
import io.grooviter.memento.bookkeeper.infra.eventstore.TopicAwareEvent
import io.grooviter.memento.bookkeeper.projector.application.port.in.UseCases
import io.micronaut.runtime.event.annotation.EventListener

import javax.inject.Inject
import javax.inject.Singleton

@Slf4j
@Singleton
class AccountCreatedListener {

    @Inject
    UseCases.StoreNewAccount storeNewAccount

    @EventListener
    void onAccountCreated(TopicAwareEvent event) {
        if (event.topic != 'ACCOUNT_CREATED') return

        log.debug("materializing new account")
        Map<String, String> info = new JsonSlurper().parseText(event.json)
        UseCases.StoreNewAccount.Params params = UseCases.StoreNewAccount.Params.builder()
            .id(UUID.fromString(info.aggregateId))
            .holderName(info.name)
            .build()

        storeNewAccount.store(params)
    }
}
