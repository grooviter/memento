package io.grooviter.memento.cargo.query.participant.adapter.eventstore.mappings

import io.grooviter.memento.cargo.query.common.adapter.eventstore.QueryMappingsContainer
import io.grooviter.memento.cargo.query.participant.adapter.eventstore.events.Created
import io.grooviter.memento.model.Mappings

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
