package io.grooviter.memento.tck.model.events

import groovy.transform.builder.Builder
import io.grooviter.memento.model.Event
import io.grooviter.memento.tck.model.Document

@Builder(includeSuperProperties = true, excludes = ['id', 'createdAt'])
class ContentModified extends Event<Document> {
    String content
}
