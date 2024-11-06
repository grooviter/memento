package memento.guide.aggregate.design.chain

import memento.EventStore
import memento.Memento
import memento.guide.aggregate.design.chain.PatientCase
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Shared
import spock.lang.Specification

class ChainDesignSpec extends Specification {
    @Rule
    TemporaryFolder temporaryFolder

    @Shared
    File events, snapshots

    @Shared
    EventStore eventStore

    void setup() {
        events = temporaryFolder.newFile()
        snapshots = temporaryFolder.newFile()
        eventStore = Memento.builder()
            .csvStorage(events, snapshots)
            .jacksonSerde()
            .onEvent(Object::println)
            .build()
    }

    void 'creating a method chain aggregate'() {
        when:
        // tag::chained[]
        PatientCase patientCase = PatientCase.opened("U92323")
            .testApplied("X-Ray")
            .diagnosisConfirmed("doctor-10001")
            .drugPrescribed("drug-1110")
            .caseClosed()

        eventStore.save(patientCase)
        // end::chained[]
        and:
        patientCase.eventList.size() == 5

        then:
        with(patientCase) {
            patientId == 'U92323'
            prescribedDrug == 'drug-1110'
            testApplied == 'X-Ray'
            caseOpenedAt < caseClosedAt
        }
    }
}