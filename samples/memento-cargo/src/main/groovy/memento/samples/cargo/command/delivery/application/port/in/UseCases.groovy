package memento.samples.cargo.command.delivery.application.port.in

import groovy.transform.builder.Builder

class UseCases {

    interface RequestNewDeliveryCommand {
        @Builder
        class Params {
            UUID userId
        }

        UUID requested(Params params)
    }

    interface DeclareAsPreparedCommand {
        @Builder
        class Params {
            UUID deliveryId
            UUID preparedBy
            String comments
        }

        void prepared(Params params)
    }

    interface LoadInTrailerCommand {
        @Builder
        class Params {
            UUID deliveryId
            UUID loadedBy
            String comments
        }

        void loaded(Params params)
    }

    interface UnloadFromTrailerCommand {
        @Builder
        class Params {
            UUID deliveryId
            UUID unloadedBy
            String comments
        }

        void unloaded(Params params)
    }

    interface DeclareAsReceivedCommand {
        @Builder
        class Params {
            UUID deliveryId
            UUID receivedBy
            String comments
        }

        void received(Params params)
    }
}
