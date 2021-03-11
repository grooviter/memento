package io.grooviter.memento.impl.csv

import io.grooviter.memento.EventStoragePort
import io.grooviter.memento.SerdePort
import io.grooviter.memento.model.Aggregate
import io.grooviter.memento.model.Event
import groovy.transform.TupleConstructor

import java.nio.file.Files
import java.util.function.Predicate
import java.util.stream.Stream

@TupleConstructor
class CsvEventStorage implements EventStoragePort {

    static final String SEPARATOR = '\\|'

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
    void append(Event event, SerdePort serdePort) {
        eventStorageFile.append(Mappers.toEventRow(event, serdePort))
    }

    @Override
    void snapshot(Aggregate aggregate, SerdePort serdePort) {
        snapshotStorageFile.append(Mappers.toSnapshotRow(aggregate, serdePort))
    }

    @Override
    Optional<Aggregate> findByAggregateIdOrderByVersionDesc(UUID id, SerdePort serdePort) {
        return Files.lines(snapshotStorageFile.toPath())
            .filter(byAggregateId(id))
            .map(line -> Mappers.toAggregate(line, serdePort))
            .sorted { left, right -> right.version - left.version }
            .limit(1)
            .findFirst()
    }

    @Override
    Stream<Event> findAllByAggregateIdAndVersionGreaterThanOrderByVersion(UUID aggregateId, Integer version, SerdePort serdePort) {
        return Files.lines(eventStorageFile.toPath())
            .filter(byAggregateId(aggregateId))
            .map(row -> Mappers.toEvent(row, serdePort))
            .filter(byVersionGreaterEqual(version))
    }

    private static Predicate<String> byAggregateId(UUID id) {
        return (String row) -> row.split(SEPARATOR)[1] == id.toString()
    }

    private static Predicate<Event> byVersionGreaterEqual(Integer version) {
        return (Event event) -> event.version >= version
    }

    @Override
    Stream<Event> findAllByAggregateId(UUID aggregateId, SerdePort serdePort) {
        return Files.lines(eventStorageFile.toPath())
            .filter(row -> row.split(SEPARATOR)[1] == aggregateId.toString())
            .map(row -> Mappers.toEvent(row, serdePort))
    }
}
