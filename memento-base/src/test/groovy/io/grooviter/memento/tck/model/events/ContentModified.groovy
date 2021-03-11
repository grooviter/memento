package io.grooviter.memento.tck.model.events

import groovy.transform.builder.Builder
import io.grooviter.memento.model.Event

@Builder(includeSuperProperties = true)
class ContentModified extends Event {
    String content
}
