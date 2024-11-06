package memento.guide.aggregate.design.voidtype

import groovy.transform.TupleConstructor
import memento.model.Event

@TupleConstructor
class DiagnosisConfirmed extends Event<memento.guide.aggregate.design.voidtype.PatientCase> {
    String diagnosedByDoctorId
}
