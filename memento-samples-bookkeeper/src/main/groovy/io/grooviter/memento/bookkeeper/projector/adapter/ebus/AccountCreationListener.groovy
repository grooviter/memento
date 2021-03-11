package io.grooviter.memento.bookkeeper.projector.adapter.ebus

import com.google.common.eventbus.Subscribe
import io.grooviter.memento.bookkeeper.projector.application.port.in.UseCases
import io.grooviter.memento.model.Event

class AccountCreationListener {
    UseCases.ProjectAccountCreationCommand materializeAccountCreation

    @Subscribe
    void onAccountCreation(Event event) {
        UseCases.ProjectAccountCreationCommand.Params params = UseCases.ProjectAccountCreationCommand.Params.builder()
            .accountId(event.aggregateId)
            .holderName("unknown")
            .createdAt(event.createdAt)
            .build()

        materializeAccountCreation.materialize(params)
    }
}
