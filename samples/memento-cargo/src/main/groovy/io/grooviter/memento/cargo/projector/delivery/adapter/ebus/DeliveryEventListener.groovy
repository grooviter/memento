package io.grooviter.memento.cargo.projector.delivery.adapter.ebus

import groovy.util.logging.Slf4j
import io.grooviter.memento.cargo.projector.delivery.application.port.in.UseCases
import io.grooviter.memento.cargo.projector.delivery.domain.events.Created
import io.grooviter.memento.cargo.projector.delivery.domain.events.OnRoute
import io.grooviter.memento.cargo.projector.delivery.domain.events.Delivered
import io.micronaut.runtime.event.annotation.EventListener

import javax.inject.Inject
import javax.inject.Singleton

@Slf4j
@Singleton
@SuppressWarnings("unused")
class DeliveryEventListener {

    @Inject
    UseCases.CreateDeliveryCommand createDeliveryCommand

    @Inject
    UseCases.SetOnRouteCommand setOnRouteCommand

    @Inject
    UseCases.MarkAsDeliveredCommand markAsDeliveredCommand

    @EventListener
    void onCreated(Created event) {
        log.debug("projecting a created delivery")
        UseCases.CreateDeliveryCommand.Params params = UseCases.CreateDeliveryCommand.Params.builder()
            .deliveryId(event.deliveryId)
            .requestedAt(event.createdAt)
            .build()

        createDeliveryCommand.create(params)
    }

    @EventListener
    void onRoute(OnRoute onRoute) {
        log.debug("projecting a delivery as on route")
        UseCases.SetOnRouteCommand.Params params = UseCases.SetOnRouteCommand.Params.builder()
            .deliveryId(onRoute.deliveryId)
            .since(onRoute.onRouteSince)
            .build()

        setOnRouteCommand.setOnRoute(params)
    }

    @EventListener
    void onReceived(Delivered received) {
        log.debug("projecting a delivery as received")
        UseCases.MarkAsDeliveredCommand.Params params = UseCases.MarkAsDeliveredCommand.Params.builder()
            .deliveryId(received.deliveryId)
            .deliveredAt(received.deliveredAt)
            .build()

        markAsDeliveredCommand.markAsDelivered(params)
    }
}
