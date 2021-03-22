package io.grooviter.memento.cargo.query.delivery.adapter.rest

import io.grooviter.memento.cargo.query.delivery.application.port.in.UseCases
import io.grooviter.memento.cargo.query.delivery.domain.Delivery
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.swagger.v3.oas.annotations.tags.Tag

import javax.inject.Inject

@Tag(name = 'DELIVERY')
@Controller('/delivery')
class DeliveryQueryController {

    @Inject
    UseCases.GetDeliveryStatusQuery getDeliveryStatus

    @Get('{deliveryId}')
    Optional<Delivery> getStatus(UUID deliveryId) {
        UseCases.GetDeliveryStatusQuery.Params params = UseCases.GetDeliveryStatusQuery.Params.builder()
            .deliveryId(deliveryId)
            .build()

        return getDeliveryStatus.getDeliveryStatus(params)
    }
}
