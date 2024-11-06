package memento.guide.aggregate.design.raw

import memento.EventStore
import memento.Memento
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Shared
import spock.lang.Specification

class RawDesignSpec extends Specification {
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

    void 'using aggregate raw design'() {
        when:
        // tag::usage[]
        PatientCase patientCase = new PatientCase(UUID.randomUUID())
            .apply(new CaseOpened())
            .apply(new TestDone("X-Ray"))
            .apply(new DiagnosisConfirmed("doctor-1000"))
            .apply(new DrugApplied("drug-1000"))
            .apply(new CaseClosed())

        eventStore.save(patientCase)
        // end::usage[]
        and:
        PatientCase loaded = eventStore.load(patientCase.id, PatientCase).get()

        then:
        patientCase.eventList.size() == 5

        and:
        with(loaded) {
            loaded.id == patientCase.id
            loaded.testApplied == patientCase.testApplied
            loaded.prescribedDrug == patientCase.prescribedDrug
            loaded.diagnosedByDoctorId == patientCase.diagnosedByDoctorId
            loaded.caseOpenedAt == patientCase.caseOpenedAt
            loaded.caseClosedAt == patientCase.caseClosedAt
        }
    }
}
