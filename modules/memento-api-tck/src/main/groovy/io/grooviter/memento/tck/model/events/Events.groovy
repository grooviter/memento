package io.grooviter.memento.tck.model.events

final class Events {

    static DocumentCreated created(String title, String author) {
        return DocumentCreated.builder().title(title).author(author).build()
    }

    static DocumentDeleted deleted() {
        return new DocumentDeleted()
    }

    static ContentModified modified(String content) {
        return ContentModified.builder().content(content).build()
    }
}
