package io.grooviter.memento.bank.infra

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties('bookkeeper')
class Configuration {

    Snapshots snapshots = new Snapshots()
    Csv csv = new Csv()
    Projections projections = new Projections()

    @ConfigurationProperties('snapshots')
    static class Snapshots {
        Integer threshold
    }

    @ConfigurationProperties('csv')
    static class Csv {
        File events
        File snapshots
    }

    @ConfigurationProperties('projections')
    static class Projections {
        File accounts
        File balances
    }
}

