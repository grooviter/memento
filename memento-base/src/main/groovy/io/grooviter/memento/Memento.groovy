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

    /**
     * Builds an {@link EventStore} with the content of the closure passed as parameter. The
     * closure can use methods found in {@link MementoBuilder}
     *
     * @param builder a closure delegating to {@link MementoBuilder}
     * @return a {@link EventStore}
     * @since 0.1.0
     */
    static EventStore build(@DelegatesTo(MementoBuilder) Closure builder) {
        Closure<MementoBuilder> mementoBuilderClosure = builder.rehydrate(new MementoBuilder(), this, this)
        MementoBuilder mementoBuilder = (mementoBuilderClosure() ?: new MementoBuilder()) as MementoBuilder

        return mementoBuilder.build()
    }
}
