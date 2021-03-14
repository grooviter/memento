package io.grooviter.memento.bookkeeper.projector.application.services

import groovy.util.logging.Slf4j
import io.grooviter.memento.bookkeeper.projector.application.port.in.UseCases
import io.grooviter.memento.bookkeeper.projector.application.port.out.ProjectorPorts
import io.grooviter.memento.bookkeeper.projector.domain.Account

import javax.inject.Inject
import javax.inject.Singleton

@Slf4j
@Singleton
class StoreNewAccountService implements UseCases.StoreNewAccount {

    @Inject
    ProjectorPorts.SaveAccountPort saveAccountPort

    @Override
    void store(Params params) {
        Account newAccount = Account.builder()
            .id(params.id?.toString())
            .name(params.holderName)
            .createdAt(params.createdAt)
            .build()

        saveAccountPort.save(newAccount)
    }
}
