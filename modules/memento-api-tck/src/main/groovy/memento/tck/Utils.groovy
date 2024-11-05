package memento.tck

class Utils {

    static File tempCsv(String name) {
        return File.createTempFile("memento-$name", '.csv')
    }

    static File tempEventsFile() {
        return tempCsv('events')
    }

    static File tempSnapshotsFile() {
        return tempCsv('snapshots')
    }
}
