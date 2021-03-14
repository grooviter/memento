package io.grooviter.memento.tck.model.events

import groovy.transform.builder.Builder
import io.grooviter.memento.model.Event

@Builder(includeSuperProperties = true, excludes = ['id', 'createdAt'])
class DocumentCreated extends Event {
    String title
    String author
}
