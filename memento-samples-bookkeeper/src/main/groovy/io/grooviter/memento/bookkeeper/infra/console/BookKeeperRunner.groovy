package io.grooviter.memento.bookkeeper.infra.console

import com.google.inject.Guice
import com.google.inject.Injector
import io.grooviter.memento.bookkeeper.account.adapter.AccountModule
import org.fusesource.jansi.AnsiConsole
import org.jline.console.SystemRegistry
import org.jline.console.impl.SystemRegistryImpl
import org.jline.reader.EndOfFileException
import org.jline.reader.LineReader
import org.jline.reader.LineReaderBuilder
import org.jline.reader.MaskingCallback
import org.jline.reader.Parser
import org.jline.reader.UserInterruptException
import org.jline.reader.impl.DefaultParser
import org.jline.terminal.Size
import org.jline.terminal.Terminal
import org.jline.terminal.TerminalBuilder
import org.jline.utils.Display
import picocli.CommandLine
import picocli.shell.jline3.PicocliCommands

import java.nio.file.Path
import java.nio.file.Paths
import java.util.function.Supplier

class BookKeeperRunner {

    BookKeeperCliFactory factory

    static BookKeeperRunner create(Injector injector) {
        return new BookKeeperRunner(
            factory: new BookKeeperCliFactory(injector)
        )
    }

    void run() {
        AnsiConsole.systemInstall()
        try {
            Supplier<Path> workDir = () -> Paths.get(System.getProperty("user.dir"))
            // set up picocli commands
            AllCommands commands = new AllCommands()
            CommandLine cmd = new CommandLine(commands, factory)
            PicocliCommands picocliCommands = new PicocliCommands(cmd)
            Parser parser = new DefaultParser()

            try (Terminal terminal = TerminalBuilder.builder().build()) {
                SystemRegistry systemRegistry = new SystemRegistryImpl(parser, terminal, workDir, null)
                systemRegistry.setCommandRegistries(picocliCommands)
                systemRegistry.register("help", picocliCommands)

                LineReader reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .completer(systemRegistry.completer())
                    .parser(parser)
                    .variable(LineReader.LIST_MAX, 50)   // max tab completion candidates
                    .build()

                cmd.colorScheme.ansi().println(cmd.colorScheme.ansi().string(Constants.BANNER))
                factory.setTerminal(terminal)

                // start the shell and process input until the user quits with Ctrl-D
                String line
                while (true) {
                    try {
                        systemRegistry.cleanUp()
                        line = reader.readLine(Constants.PROMPT, Constants.PROMPT_RIGHT, (MaskingCallback) null, null)
                        systemRegistry.execute(line)
                    } catch (UserInterruptException e) {
                        // Ignore
                    } catch (EndOfFileException e) {
                        return
                    } catch (Exception e) {
                        systemRegistry.trace(e)
                    }
                }
            }
        } catch (Throwable t) {
            t.printStackTrace()
        } finally {
            AnsiConsole.systemUninstall()
        }

    }
}
