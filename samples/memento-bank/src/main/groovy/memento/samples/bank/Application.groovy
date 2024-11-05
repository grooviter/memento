package memento.samples.bank

import memento.samples.bank.infra.console.BookKeeperRunner

class Application {
    static void main(String[] args) throws Exception {
        BookKeeperRunner.create(args).run()
    }
}
