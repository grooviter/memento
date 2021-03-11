package io.grooviter.memento.bookkeeper.projector.application.services

import io.grooviter.memento.bookkeeper.projector.application.port.in.UseCases
import io.grooviter.memento.bookkeeper.projector.application.port.out.ProjectorPorts
import io.grooviter.memento.bookkeeper.projector.domain.AccountCreated

class AccountCreationProjectionService implements UseCases.ProjectAccountCreationCommand {
    ProjectorPorts.SaveAccountCreatedPort saveAccountCreatedPort

    @Override
    void materialize(Params params) {
        AccountCreated accountCreated = AccountCreated.builder()
            .accountId(params.accountId)
            .holderName(params.holderName)
            .createdAt(params.createdAt)
            .build()

        saveAccountCreatedPort.save(accountCreated)
    }
}
