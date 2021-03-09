package io.grooviter.memento

import io.grooviter.memento.model.Aggregate
import io.grooviter.memento.model.Event

import java.util.stream.Stream

/**
 * Responsible for storing and retrieving events keeping and
 * immutable stream of events. It follows some of the ideas
 * contained at:
 *
 * https://martinfowler.com/eaaDev/EventSourcing.html
 *
 * @since 0.1.0
 */
interface EventStore {

    /**
     * Appends a new event to the stream
     *
     * @param event instance of {@link Event}
     * @since 0.1.0
     */
    void append(Event event)

    /**
     * Lists all events from a given aggregate from a specific version
     *
     * @param aggregateId aggregate id
     * @param version version we want to start from
     * @return an {@link Stream} of events
     * @since 0.1.0
     */
    Stream<Event> listEvents(UUID aggregateId, Integer version)

    /**
     * Lists all events from a given aggregate from the beginning of the stream
     *
     * @param aggregateId aggregate id
     * @return an {@link Stream} of events
     * @since 0.1.0
     */
    Stream<Event> listEvents(UUID aggregateId)

    /**
     * Loads the latest snapshot of the aggregate
     *
     * @param aggregateId
     * @param aggregateType
     * @return
     * @since 0.1.0
     */
    public <T extends Aggregate> Optional<T> load(UUID aggregateId, Class<T> aggregateType)

    /**
     * Saves a given aggregate events
     *
     * @param aggregate the {@link Aggregate} to save
     * @since 0.1.0
     */
    void save(Aggregate aggregate)

    /**
     * Creates a new snapshot of the aggregate
     *
     * @param aggregate to take an snapshot from
     * @since 0.1.0
     */
    void snapshot(Aggregate aggregate)

}