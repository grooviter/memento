package io.grooviter.memento.bookkeeper.infra.console

import com.google.inject.Injector
import org.jline.terminal.Terminal
import picocli.CommandLine

class BookKeeperCliFactory implements CommandLine.IFactory {
    private final CommandLine.IFactory fallbackFactory = CommandLine.defaultFactory();

    Injector injector
    Terminal terminal

    BookKeeperCliFactory(Injector injector) {
        this.injector = injector
    }

    @Override
    public <K> K create(Class<K> cls) throws Exception {
        return Optional
            .ofNullable(injector.getInstance(cls))
            .orElse(fallbackFactory.create(cls))
    }
}
