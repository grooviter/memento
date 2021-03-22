package io.grooviter.memento.cargo.command.delivery.domain

import io.grooviter.memento.cargo.command.delivery.domain.events.Loaded
import io.grooviter.memento.cargo.command.delivery.domain.events.Prepared
import io.grooviter.memento.cargo.command.delivery.domain.events.Received
import io.grooviter.memento.cargo.command.delivery.domain.events.Requested
import io.grooviter.memento.cargo.command.delivery.domain.events.Unloaded
import io.grooviter.memento.model.Aggregate

import java.time.OffsetDateTime

class Delivery extends Aggregate {
    UUID deliveredTo, deliveryCurrentHolder
    Status currentStatus
    OffsetDateTime startedAt
    OffsetDateTime finishedAt

    static Delivery beginDeliveryProcess(UUID requesterId) {
        Delivery delivery = new Delivery(id: UUID.randomUUID())
        Requested requested = Requested.builder()
            .clientId(requesterId)
            .version(delivery.nextVersion)
            .build()

        return delivery.apply(requested)

    }

    Delivery prepare(UUID employeeId) {
        assert this.currentStatus == Status.REQUESTED

        Prepared prepared = Prepared.builder()
            .employeeId(employeeId)
            .version(nextVersion)
            .build()

        return this.apply(prepared)
    }

    Delivery load(UUID driverId, String comments) {
        assert this.currentStatus == Status.PREPARED

        Loaded loaded = Loaded.builder()
            .driverId(driverId)
            .comments(comments)
            .version(nextVersion)
            .build()

        return this.apply(loaded)
    }

    Delivery unload(UUID driverId, String comments) {
        assert this.currentStatus == Status.ON_ROUTE

        Unloaded unloaded = Unloaded.builder()
            .driverId(driverId)
            .comments(comments)
            .version(nextVersion)
            .build()

        return this.apply(unloaded)
    }

    Delivery claimAsReceived(UUID clientId, String comments) {
        assert this.currentStatus == Status.CLOSE_TO_POSITION

        Received received = Received.builder()
            .clientId(clientId)
            .comments(comments)
            .version(nextVersion)
            .build()

        return this.apply(received)
    }

    private Delivery apply(Requested event) {
        super.apply(event)
        this.deliveredTo = event.clientId
        this.currentStatus = Status.REQUESTED
        this.startedAt = event.createdAt
        return this
    }

    private Delivery apply(Prepared event) {
        super.apply(event)
        this.deliveryCurrentHolder = event.employeeId
        this.currentStatus = Status.PREPARED
        return this
    }

    private Delivery apply(Loaded event) {
        super.apply(event)
        this.deliveryCurrentHolder = event.driverId
        this.currentStatus = Status.ON_ROUTE
        return this
    }

    private Delivery apply(Unloaded event) {
        super.apply(event)
        this.deliveryCurrentHolder = event.driverId
        this.currentStatus = Status.CLOSE_TO_POSITION
        return this
    }

    private Delivery apply(Received event) {
        super.apply(event)
        this.deliveryCurrentHolder = event.clientId
        this.currentStatus = Status.CLOSED
        this.finishedAt = event.createdAt
        return this
    }
}
