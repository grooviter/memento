package memento.guide.aggregate.design.voidtype

import memento.EventStore
import memento.Memento
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Shared
import spock.lang.Specification

class VoidDesignSpec extends Specification {
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

    void 'creating a void type aggregate'() {
        setup:
        UUID aggregateUUID = UUID.randomUUID()
        when:
        // tag::usage[]
        // POST /api/case
        PatientCase patientCase = new PatientCase(aggregateUUID)
        patientCase.opened("patient-1000")
        eventStore.save(patientCase)

        // PUT /api/case/{UUID}/test
        patientCase = eventStore.load(aggregateUUID, PatientCase).get()
        patientCase.testApplied("X-Ray")
        eventStore.save(patientCase)

        // PUT /api/case/{UUID}/doctor
        patientCase = eventStore.load(aggregateUUID, PatientCase).get()
        patientCase.diagnosisConfirmed("doctor-1000")
        eventStore.save(patientCase)

        // PUT /api/case/{UUID}/drug
        patientCase = eventStore.load(aggregateUUID, PatientCase).get()
        patientCase.drugPrescribed("drug-1000")
        eventStore.save(patientCase)

        // PUT /api/case/close
        patientCase = eventStore.load(aggregateUUID, PatientCase).get()
        patientCase.caseClosed()
        eventStore.save(patientCase)
        // end::usage[]
        then:
        with(patientCase) {
            eventList.size() == 1 // it only has the last event since last reloading
            patientId == 'patient-1000'
            testApplied == 'X-Ray'
            diagnosedByDoctorId == 'doctor-1000'
            caseOpenedAt < caseClosedAt
        }
    }
}
