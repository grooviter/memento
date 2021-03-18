package io.grooviter.memento.tck.model.events

final class Events {

    static DocumentCreated created(String title, String author, Integer version) {
        return DocumentCreated.builder().title(title).author(author).version(version).build()
    }

    static DocumentDeleted deleted(Integer version) {
        return new DocumentDeleted(version: version)
    }

    static ContentModified modified(String content, Integer version) {
        return ContentModified.builder().content(content).version(version).build()
    }
}
