package io.grooviter.memento.bank.infra.console

import de.vandermeer.asciitable.AsciiTable
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment

import java.util.function.Function
import java.util.stream.Collectors

class Utils {

    static class TableBuilder<T> {
        TableBuilder(Class<T> type) {
            this.type = type
        }
        List<Function<T, ?>> consumers = []
        Class<T> type
        Integer width = 120
        List<String> columnNames = []
        List<T> rows = []

        TableBuilder<T> columnNames(String... names) {
            columnNames.addAll(names)
            return this
        }

        TableBuilder<T> addColumnConsumer(Function<T, ?> func) {
            consumers.add(func)
            return this
        }

        TableBuilder<T> setTableWidth(int width) {
            this.width = width
            return this
        }

        TableBuilder<T> rows(List<T> rows) {
            this.rows = rows
            return this
        }

        String build() {
            AsciiTable asciiTable = new AsciiTable()
            asciiTable.addRule()
            asciiTable.addRow(this.columnNames).setTextAlignment(TextAlignment.CENTER)
            this.rows
                .stream()
                .forEach(bean -> {
                    asciiTable.addRule()
                    List<String> rowValues = consumers.stream()
                        .map(p -> p?.apply(bean)?.toString())
                        .collect(Collectors.toList())
                    asciiTable.addRow(rowValues)
                })
            asciiTable.addRule()
            return asciiTable.render(this.width)
        }
    }

    static <T> TableBuilder<T> tableBuilder(Class<T> type) {
        return new TableBuilder<T>(type)
    }
}
