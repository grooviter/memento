package memento.samples.bank.infra.persistence

import com.opencsv.CSVReader
import com.opencsv.CSVWriter
import com.opencsv.bean.CsvToBeanBuilder
import com.opencsv.bean.StatefulBeanToCsv
import com.opencsv.bean.StatefulBeanToCsvBuilder

import java.nio.file.Files
import java.util.stream.Stream

class Utils {

    static <T> Stream<T> createStreamForFile(File source, Class<T> type) {
        return Optional.ofNullable(source)
            .filter(file -> file.exists())
            .map(FileReader::new)
            .map(CSVReader::new)
            .map(CsvToBeanBuilder::new)
            .map(builder -> builder.withType(type).build().stream())
            .orElse(Stream<T>.empty())
    }

    static <T> void appendToCsvFile(File fileToAppend, T object) {
        if (!fileToAppend.exists()) {
            Files.createDirectories(fileToAppend.parentFile.toPath())
            Files.createFile(fileToAppend.toPath())
        }

        try(FileWriter writer = new FileWriter(fileToAppend, true)) {
            StatefulBeanToCsv<T> accountWriter = new StatefulBeanToCsvBuilder(writer)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build()
            accountWriter.write(object)
        }
    }
}
