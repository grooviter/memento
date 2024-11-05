package memento.samples.bank.account.application.port.in

import groovy.transform.builder.Builder

final class UseCases {

    interface CreateAccountCommand {
        @Builder
        static class Params {
            String holderName
            String iban
        }

        UUID create(Params params)
    }

    interface DepositCommand {
        @Builder
        static class Params {
            UUID accountId
            BigDecimal amount
        }

        void deposit(Params params)
    }

    interface WithdrawCommand {
        @Builder
        static class Params {
            UUID accountId
            BigDecimal amount
        }

        void withdraw(Params params)
    }

    interface ApplyCommissionCommand {
        @Builder
        static class Params {
            UUID accountId
            BigDecimal amount
        }

        void commission(Params params)
    }

    interface CloseAccountCommand {
        @Builder
        static class Params {
            UUID accountId
            String comment
        }

        void close(Params params)
    }
}
