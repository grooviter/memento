package io.grooviter.memento.bank.projector.application.port.in

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

    interface UpdateAccountBalance {
        @Builder
        class Params {
            UUID id
            BigDecimal newBalance
            OffsetDateTime createdAt
        }
        void store(Params params)
    }
}
