package memento.guide.aggregate.design.chain

import groovy.transform.TupleConstructor
import memento.model.Event

@TupleConstructor(includes = ['patientId'])
class CaseOpened extends Event<PatientCase> {
    String patientId
    Date caseOpenedAt = new Date()
}
