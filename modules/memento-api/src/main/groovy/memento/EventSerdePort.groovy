package memento


import memento.model.Aggregate
import memento.model.Event

interface EventSerdePort {

    String aggregateToJSON(Aggregate aggregate)
    String eventToJSON(Event event)
    String objectToJSON(Object o)

    Aggregate aggregateFromJSON(String alias, String json)
    Event eventFromJSON(String alias, String json)

    String resolveAlias(Class clazz)
}