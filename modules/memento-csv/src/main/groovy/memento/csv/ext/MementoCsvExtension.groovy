package memento.csv.ext


import memento.impl.MementoBuilder

class MementoCsvExtension {
    static MementoBuilder csvStorage(MementoBuilder builder, File eventsFile, File snapshotsFile) {
        return builder.eventStorage(memento.csv.CsvEventStorage.create(eventsFile, snapshotsFile))
    }

    static MementoBuilder csvStorage(MementoBuilder builder, String eventsFilePath, String snapshotsFilePath) {
        return builder.eventStorage(memento.csv.CsvEventStorage.create(new File(eventsFilePath), new File (snapshotsFilePath)))
    }
}
