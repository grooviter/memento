package memento.csv

import memento.EventStoragePort
import memento.EventSerdePort
import memento.model.Aggregate
import memento.model.Event
import groovy.transform.TupleConstructor

import java.nio.file.Files
import java.util.function.Predicate
import java.util.stream.Stream

@TupleConstructor
class CsvEventStorage implements EventStoragePort {

    static final String SEPARATOR = '\\|'

    static final Integer ROW_COLUMN_AGGREGATE_ID = 1
    static final Integer ROW_COLUMN_EVENT_TYPE = 3

    File eventStorageFile
    File snapshotStorageFile

    static CsvEventStorage create(File events, File snapshots) {
        if (!events.exists()) {
            events.createNewFile()
        }
        if (!snapshots.exists()) {
            snapshots.createNewFile()
        }

        return new CsvEventStorage(events, snapshots)
    }

    @Override
    void append(Event event, EventSerdePort serdePort) {
        eventStorageFile.append(Mappers.toEventRow(event, serdePort))
    }

    @Override
    void snapshot(Aggregate aggregate, EventSerdePort serdePort) {
        snapshotStorageFile.append(Mappers.toSnapshotRow(aggregate, serdePort))
    }

    @Override
    Optional<Aggregate> findByAggregateIdOrderByVersionDesc(UUID id, EventSerdePort serdePort) {
        return Files.lines(snapshotStorageFile.toPath())
            .filter(byAggregateId(id))
            .map(line -> Mappers.toAggregate(line, serdePort))
            .sorted { left, right -> right.version - left.version }
            .limit(1)
            .findFirst()
    }

    @Override
    Stream<Event> findAllByAggregateIdAndVersionGreaterThanOrderByVersion(UUID aggregateId, Integer version, EventSerdePort serdePort) {
        return Files.lines(eventStorageFile.toPath())
            .filter(byAggregateId(aggregateId))
            .map(row -> Mappers.toEvent(row, serdePort))
            .filter(byVersionGreaterThan(version))
    }

    private static Predicate<String> byAggregateId(UUID id) {
        return (String row) -> row.split(SEPARATOR)[ROW_COLUMN_AGGREGATE_ID] == id.toString()
    }

    private static Predicate<Event> byVersionGreaterThan(Integer version) {
        return (Event event) -> event.version > version
    }

    @Override
    Stream<Event> findAllByAggregateId(UUID aggregateId, EventSerdePort serdePort) {
        return Files.lines(eventStorageFile.toPath())
            .filter(row -> row.split(SEPARATOR)[ROW_COLUMN_AGGREGATE_ID] == aggregateId.toString())
            .map(row -> Mappers.toEvent(row, serdePort))
    }

    @Override
    Stream<Event> findAllByAliases(String[] aliases, EventSerdePort serdePort) {
        return Files.lines(eventStorageFile.toPath())
            .filter(row -> row.split(SEPARATOR)[ROW_COLUMN_EVENT_TYPE] in aliases)
            .map(row -> Mappers.toEvent(row, serdePort))
    }
}
