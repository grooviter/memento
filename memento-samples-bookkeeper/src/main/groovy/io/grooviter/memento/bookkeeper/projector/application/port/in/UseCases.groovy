package io.grooviter.memento.bookkeeper.projector.application.port.in

import groovy.transform.builder.Builder

import java.time.OffsetDateTime

final class UseCases {

    interface ProjectAccountCreationCommand {
        @Builder
        class Params {
            UUID accountId
            String holderName
            OffsetDateTime createdAt
        }

        void materialize(Params params)
    }
}
