package memento.guide.aggregate.design.voidtype

import groovy.contracts.Ensures
import groovy.transform.InheritConstructors
import memento.model.Aggregate

@InheritConstructors
class PatientCase extends Aggregate implements MedicalProcedure {
    String patientId
    String diagnosedByDoctorId
    String testApplied
    String prescribedDrug
    Date caseOpenedAt, caseClosedAt

    @Ensures({ patientId })
    void opened(String patientId) {
        this.apply(new CaseOpened(patientId))
    }

    void testApplied(String test) {
        this.apply(new TestDone(test))
    }

    @Ensures({ testApplied })
    void diagnosisConfirmed(String doctorId) {
        this.apply(new DiagnosisConfirmed(doctorId))
    }

    @Ensures({ diagnosedByDoctorId })
    void drugPrescribed(String drug) {
        this.apply(new DrugApplied(drug))
    }

    @Ensures({ diagnosedByDoctorId && caseOpenedAt < caseClosedAt })
    void caseClosed() {
        this.apply(new CaseClosed())
    }

    @Override
    void configure() {
        bind(CaseClosed, CaseOpened, DiagnosisConfirmed, DrugApplied, TestDone)
    }
}
