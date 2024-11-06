package memento.guide.aggregate.design.chain

import groovy.contracts.Ensures
import groovy.transform.InheritConstructors
import memento.model.Aggregate

@InheritConstructors
class PatientCase extends Aggregate {
    String patientId
    String diagnosedByDoctorId
    String testApplied
    String prescribedDrug
    Date caseOpenedAt, caseClosedAt

    @Ensures({ patientId })
    static PatientCase opened(String patientId) {
        return new PatientCase(UUID.randomUUID()).apply(new CaseOpened(patientId))
    }

    PatientCase testApplied(String test) {
        return this.apply(new TestDone(test))
    }

    @Ensures({ testApplied })
    PatientCase diagnosisConfirmed(String doctorId) {
        return this.apply(new DiagnosisConfirmed(doctorId))
    }

    @Ensures({ diagnosedByDoctorId })
    PatientCase drugPrescribed(String drug) {
        return this.apply(new DrugApplied(drug))
    }

    @Ensures({ diagnosedByDoctorId && caseOpenedAt < caseClosedAt })
    PatientCase caseClosed() {
        return this.apply(new CaseClosed())
    }

    @Override
    void configure() {
        bind(CaseClosed, CaseOpened, DiagnosisConfirmed, DrugApplied, TestDone)
    }
}
