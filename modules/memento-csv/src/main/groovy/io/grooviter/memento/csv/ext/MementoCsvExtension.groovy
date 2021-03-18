package io.grooviter.memento.csv.ext

import io.grooviter.memento.csv.CsvEventStorage
import io.grooviter.memento.impl.MementoBuilder

class MementoCsvExtension {
    static MementoBuilder csvStorage(MementoBuilder builder, File eventsFile, File snapshotsFile) {
        return builder.eventStorage(new CsvEventStorage(eventsFile, snapshotsFile))
    }
}
