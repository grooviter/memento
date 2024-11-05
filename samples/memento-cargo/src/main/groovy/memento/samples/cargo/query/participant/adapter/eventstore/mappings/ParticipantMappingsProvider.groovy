package memento.samples.cargo.query.participant.adapter.eventstore.mappings

import memento.samples.cargo.query.common.adapter.eventstore.QueryMappingsContainer
import memento.samples.cargo.query.participant.adapter.eventstore.events.Created
import memento.model.Mappings

import jakarta.inject.Provider
import jakarta.inject.Singleton

@Singleton
class ParticipantMappingsProvider implements Provider<QueryMappingsContainer> {

    QueryMappingsContainer get() {
        Mappings mappings = Mappings.builder()
            .addMapping('PARTICIPANT_REGISTERED', Created)
            .build()

        return new QueryMappingsContainer(mappings: mappings)
    }
}
