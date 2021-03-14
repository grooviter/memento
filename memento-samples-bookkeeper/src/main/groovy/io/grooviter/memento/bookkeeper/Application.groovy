package io.grooviter.memento.bookkeeper

import io.grooviter.memento.bookkeeper.infra.console.BookKeeperRunner

class Application {
    static void main(String[] args) throws Exception {
        BookKeeperRunner.create(args).run()
    }
}
