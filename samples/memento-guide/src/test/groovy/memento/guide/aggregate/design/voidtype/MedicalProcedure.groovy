package memento.guide.aggregate.design.voidtype

interface MedicalProcedure {
    void opened(String patientId)
    void testApplied(String test)
    void diagnosisConfirmed(String doctorId)
    void drugPrescribed(String drug)
    void caseClosed()
}
