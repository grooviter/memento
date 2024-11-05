package memento.model

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.stc.ClosureParams
import groovy.transform.stc.FromString

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

    /**
     * @since 0.1.0
     */
    @JsonIgnore
    Integer eventsLoaded = 0

    /**
     * @since 0.1.0
     */
    private Map<Class, Closure> bindFunctions = [:]

    /**
     * @since 0.1.0
     */
    Aggregate() {
        this.configure()
    }

    /**
     * @since 0.1.0
     */
    Aggregate(UUID id) {
        this.id = id
        this.configure()
    }

    /**
     * Applies basic properties to aggregate
     *
     * @param event an instance of type {@link Event}
     * @since 0.1.0
     */
    <U extends Aggregate> U apply(Event event) {
        this.bindFunctions[event.class].call(this, event)
        event.version = this.nextVersion

        if (event.aggregateId){
            assert this.id == event.aggregateId
        }

        event.aggregateId = this.id
        event.id      = event.id ?: UUID.randomUUID()
        event.version = event.version ?: nextVersion

        this.version = event.version
        this.eventList.add(event)
        this.eventsLoaded++

        return (U) this
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
     * @since 0.1.0
     */
    void configure(){}

    /**
     * @Param eventClass
     * @since 0.1.0
     */
    protected <A extends Aggregate, E extends Event<A>> void bind(Class<E> eventClass){
        this.bindFunctions[eventClass] = { A agg, E event ->
            agg.class.declaredFields
                .findAll { it.name !in ['__$stMC', '$staticClassInfo', '$callSiteArray']}
                .findAll { it.name in event.class.declaredFields.name }
                .each {
                    agg[it.name] = event[it.name]
                }
        }
    }

    protected <A extends Aggregate, E extends Event<A>> void bind(Class<E>[] eventClasses) {
        eventClasses.each(this::bind)
    }
    /**
     * @param eventClass
     * @param func
     * @since 0.1.0
     */
    protected <A extends Aggregate, E extends Event<A>> void bind(
        Class<E> eventClass,
        @ClosureParams(value=FromString, options=["A,E"]) Closure func){
        this.bindFunctions[eventClass] = func
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

    /**
     * @return
     * @since 0.1.0
     */
    Aggregate asSnapshot() {
        this.eventList = []
        return this
    }
}
