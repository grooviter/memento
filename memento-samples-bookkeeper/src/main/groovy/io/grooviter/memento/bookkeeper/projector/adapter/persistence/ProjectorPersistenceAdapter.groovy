package io.grooviter.memento.bookkeeper.projector.adapter.persistence

import com.opencsv.CSVReader
import com.opencsv.CSVWriter
import com.opencsv.bean.CsvToBeanBuilder
import com.opencsv.bean.StatefulBeanToCsv
import com.opencsv.bean.StatefulBeanToCsvBuilder
import io.grooviter.memento.bookkeeper.infra.Configuration
import io.grooviter.memento.bookkeeper.projector.application.port.out.ProjectorPorts
import io.grooviter.memento.bookkeeper.projector.domain.Account

import javax.inject.Inject
import javax.inject.Singleton
import java.util.stream.Stream

@Singleton
class ProjectorPersistenceAdapter implements
    ProjectorPorts.LoadAccountPort,
    ProjectorPorts.SaveAccountPort {

    @Inject
    Configuration configuration

    @Override
    Optional<Account> load(UUID id) {
        return createStreamForFile(configuration.projections.accounts, Account)
            .filter(account -> account.id == id)
            .findFirst()
    }

    @Override
    void save(Account account) {
        FileWriter writer = new FileWriter(configuration.projections.accounts, true)
        StatefulBeanToCsv<Account> accountWriter = new StatefulBeanToCsvBuilder(writer)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build()

        accountWriter.write(account)
        writer.close() // required to flush to disk
    }

    private static <T> Stream<T> createStreamForFile(File source, Class<T> type) {
        CSVReader reader = new CSVReader(new FileReader(source))
        return new CsvToBeanBuilder<T>(reader)
            .withType(type)
            .build()
            .stream()
    }
}
