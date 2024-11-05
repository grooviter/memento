package memento


import memento.model.Event
import memento.model.Aggregate

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
     * Lists all events named with the aliases passed as parameter
     *
     * @param list of mapped aliases
     * @return a {@link Stream} of events
     * @since 0.1.0
     */
    Stream<Event> listEvents(String... aliases)

    /**
     * Loads the latest snapshot of the aggregate
     *
     * @param aggregateId id of the aggregate to load
     * @param aggregateType type of the aggregate to load
     * @return an optional object maybe containing the expected aggregate
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