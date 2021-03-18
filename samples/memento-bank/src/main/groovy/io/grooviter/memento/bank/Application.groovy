package io.grooviter.memento.bank

import io.grooviter.memento.bank.infra.console.BookKeeperRunner

class Application {
    static void main(String[] args) throws Exception {
        BookKeeperRunner.create(args).run()
    }
}
