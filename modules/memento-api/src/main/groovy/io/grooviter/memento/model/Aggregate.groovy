package io.grooviter.memento.model

import com.fasterxml.jackson.annotation.JsonIgnore

import java.util.stream.Stream

/**
 * Represents an aggregate
 *
 * @since 0.1.0
 */
class Aggregate {

    /**
     * Aggregate's id
     *
     * @since 0.1.0
     */
    UUID id

    /**
     * Current version
     *
     * @since 0.1.0
     */
    Integer version = 0

    /**
     * New events applied to aggregate
     *
     * @since 0.1.0
     */
    @JsonIgnore
    List<Event> eventList = []

    @JsonIgnore
    Integer eventsLoaded = 0

    /**
     * Applies basic properties to aggregate
     *
     * @param event an instance of type {@link Event}
     * @since 0.1.0
     */
    void apply(Event event) {
        if (event.aggregateId){
            assert this.id == event.aggregateId
        }

        event.aggregateId = this.id
        event.id      = event.id ?: UUID.randomUUID()
        event.version = event.version ?: nextVersion

        this.version = event.version
        this.eventList.add(event)
        this.eventsLoaded++
    }

    /**
     * Applies a {@link Stream} of {@link Event} instances
     *
     * @param eventStream the stream of events we want to apply to the aggregate
     * @return the instance of the aggregate after events have been applied
     * @since 0.1.0
     */
    Aggregate applyEvents(Stream<Event> eventStream) {
        eventStream.forEach(this::apply)
        return this
    }

    /**
     * Returns what the next version of the aggregate could be
     *
     * @return the new possible version
     * @since 0.1.0
     */
    @JsonIgnore
    Integer getNextVersion() {
        return this.version + 1
    }

    Aggregate asSnapshot() {
        this.eventList = []
        return this
    }
}
