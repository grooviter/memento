package memento.samples.cargo.query.delivery.adapter.eventstore.producer

import groovy.util.logging.Slf4j
import memento.samples.cargo.query.delivery.application.port.UseCases
import memento.samples.cargo.query.delivery.adapter.eventstore.events.Created
import memento.samples.cargo.query.delivery.adapter.eventstore.events.Delivered
import memento.samples.cargo.query.delivery.adapter.eventstore.events.OnRoute
import io.micronaut.runtime.event.annotation.EventListener

import jakarta.inject.Inject
import jakarta.inject.Singleton

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
