package io.grooviter.memento.tck.model.events

import io.grooviter.memento.model.Event

class DocumentCreated extends Event {
    String title
    String author
}
