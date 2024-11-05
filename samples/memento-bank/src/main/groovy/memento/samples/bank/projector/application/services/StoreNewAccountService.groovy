package memento.samples.bank.projector.application.services

import groovy.util.logging.Slf4j
import memento.samples.bank.projector.application.port.UseCases
import memento.samples.bank.projector.application.port.ProjectorPorts
import memento.samples.bank.projector.domain.Account

import jakarta.inject.Inject
import jakarta.inject.Singleton

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
