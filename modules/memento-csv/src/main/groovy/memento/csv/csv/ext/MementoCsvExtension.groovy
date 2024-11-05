package memento.csv.csv.ext

import memento.csv.csv.CsvEventStorage
import memento.impl.MementoBuilder

class MementoCsvExtension {
    static MementoBuilder csvStorage(MementoBuilder builder, File eventsFile, File snapshotsFile) {
        return builder.eventStorage(CsvEventStorage.create(eventsFile, snapshotsFile))
    }

    static MementoBuilder csvStorage(MementoBuilder builder, String eventsFilePath, String snapshotsFilePath) {
        return builder.eventStorage(CsvEventStorage.create(new File(eventsFilePath), new File (snapshotsFilePath)))
    }
}
