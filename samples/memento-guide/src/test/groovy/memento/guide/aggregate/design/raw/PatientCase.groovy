package memento.guide.aggregate.design.raw

import groovy.transform.InheritConstructors
import memento.model.Aggregate

@InheritConstructors
class PatientCase extends Aggregate {
    String patientId
    String diagnosedByDoctorId
    String testApplied
    String prescribedDrug
    Date caseOpenedAt, caseClosedAt

    @Override
    void configure() {
        bind(CaseClosed, CaseOpened, DiagnosisConfirmed, DrugApplied, TestDone)
    }
}
