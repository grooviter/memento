package memento.samples.cargo.command.delivery.domain

import memento.samples.cargo.command.delivery.domain.events.Loaded
import memento.samples.cargo.command.delivery.domain.events.Prepared
import memento.samples.cargo.command.delivery.domain.events.Received
import memento.samples.cargo.command.delivery.domain.events.Requested
import memento.samples.cargo.command.delivery.domain.events.Unloaded
import memento.model.Aggregate

import java.time.OffsetDateTime

class Delivery extends Aggregate {
    UUID deliveredTo, deliveryCurrentHolder
    Status currentStatus
    OffsetDateTime startedAt
    OffsetDateTime finishedAt

    static Delivery beginDeliveryProcess(UUID requesterId) {
        return new Delivery(id: UUID.randomUUID()).apply(Requested.builder().clientId(requesterId).build())

    }

    Delivery prepare(UUID employeeId) {
        assert this.currentStatus == Status.REQUESTED

        Prepared prepared = Prepared.builder()
            .employeeId(employeeId)
            .build()

        return this.apply(prepared)
    }

    Delivery load(UUID driverId, String comments) {
        assert this.currentStatus == Status.PREPARED

        Loaded loaded = Loaded.builder()
            .driverId(driverId)
            .comments(comments)
            .build()

        return this.apply(loaded)
    }

    Delivery unload(UUID driverId, String comments) {
        assert this.currentStatus == Status.ON_ROUTE

        Unloaded unloaded = Unloaded.builder()
            .driverId(driverId)
            .comments(comments)
            .build()

        return this.apply(unloaded)
    }

    Delivery claimAsReceived(UUID clientId, String comments) {
        assert this.currentStatus == Status.CLOSE_TO_POSITION

        Received received = Received.builder()
            .clientId(clientId)
            .comments(comments)
            .build()

        return this.apply(received)
    }

    void configure() {
        bind(Requested) { agg, event ->
            agg.deliveredTo = event.clientId
            agg.currentStatus = Status.REQUESTED
            agg.startedAt = event.createdAt
        }

        bind(Prepared) { agg, event ->
            agg.deliveryCurrentHolder = event.employeeId
            agg.currentStatus = Status.PREPARED
        }

        bind(Loaded) { agg, event ->
            agg.deliveryCurrentHolder = event.driverId
            agg.currentStatus = Status.ON_ROUTE
        }

        bind(Unloaded) { agg, event ->
            agg.deliveryCurrentHolder = event.driverId
            agg.currentStatus = Status.CLOSE_TO_POSITION
        }

        bind(Received) { agg, event ->
            agg.deliveryCurrentHolder = event.clientId
            agg.currentStatus = Status.CLOSED
            agg.finishedAt = event.createdAt
        }
    }
}
