package memento.samples.bank.infra.eventstore

import memento.EventBusPort
import memento.EventSerdePort
import memento.model.Event
import io.micronaut.context.event.ApplicationEventPublisher

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class MicronautEventBus implements EventBusPort {

    @Inject
    ApplicationEventPublisher applicationEventPublisher

    @Override
    void publish(Event event, EventSerdePort serdePort) {
        String topic = serdePort.resolveAlias(event.getClass())
        String json  = serdePort.eventToJSON(event)

        applicationEventPublisher.publishEvent(new TopicAwareEvent(topic, json))
    }

    @Override
    void publishAsync(Event event, EventSerdePort serdePort) {
        String topic = serdePort.resolveAlias(event.getClass())
        String json  = serdePort.eventToJSON(event)

        applicationEventPublisher.publishEventAsync(new TopicAwareEvent(topic, json))
    }
}
