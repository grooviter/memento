package io.grooviter.memento.bookkeeper.projector.application.port.in

import groovy.transform.builder.Builder

import java.time.OffsetDateTime

final class UseCases {

    interface StoreNewAccount {
        @Builder
        class Params {
            UUID id
            String holderName
            OffsetDateTime createdAt
        }

        void store(Params params)
    }
}
