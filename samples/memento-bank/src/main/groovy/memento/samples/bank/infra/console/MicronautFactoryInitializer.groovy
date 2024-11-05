package memento.samples.bank.infra.console

import io.micronaut.configuration.picocli.MicronautFactory
import io.micronaut.context.ApplicationContext
import io.micronaut.context.ApplicationContextBuilder
import io.micronaut.context.env.CommandLinePropertySource
import io.micronaut.context.env.Environment
import io.micronaut.core.cli.CommandLine

class MicronautFactoryInitializer {
    static <R extends Runnable> MicronautFactory initialize(Class<R> commandClazz, String... args) {
        ApplicationContextBuilder builder = buildApplicationContext(commandClazz, args)
        try (ApplicationContext ctx = builder.start()) {
            return new MicronautFactory(ctx)
        }
    }

    private static ApplicationContextBuilder buildApplicationContext(Class<?> cls, String[] args) {
        CommandLine commandLine = CommandLine.parse(args)
        CommandLinePropertySource commandLinePropertySource = new CommandLinePropertySource(commandLine)
        return ApplicationContext
                .builder(cls, Environment.CLI)
                .propertySources(commandLinePropertySource)
    }
}
