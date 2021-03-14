package io.grooviter.memento

import io.grooviter.memento.model.Aggregate
import io.grooviter.memento.model.Event

interface SerdePort {

    String aggregateToJSON(Aggregate aggregate)
    String eventToJSON(Event event)
    String objectToJSON(Object o)

    Aggregate aggregateFromJSON(String alias, String json)
    Event eventFromJSON(String alias, String json)

    String resolveAlias(Class clazz)
}