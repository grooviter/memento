package io.grooviter.memento

import io.grooviter.memento.impl.MementoBuilder

/**
 * Entry point to create a new {@link EventStore}
 *
 * @since 0.1.0
 */
final class Memento {

    private Memento() {
        // empty constructor
    }

    /**
     * Creates a fluid builder to build an instance of type {@link EventStore}
     *
     * @return an instance of {@link MementoBuilder}
     * @since 0.1.0
     */
    static MementoBuilder builder() {
        return new MementoBuilder()
    }
}
