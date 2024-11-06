package memento.guide.aggregate.design.chain

import groovy.transform.TupleConstructor
import memento.model.Event

@TupleConstructor
class DiagnosisConfirmed extends Event<PatientCase> {
    String diagnosedByDoctorId
}
