package memento.guide.aggregate.design.raw

import groovy.transform.TupleConstructor
import memento.model.Event

@TupleConstructor(includes = ['patientId'])
class CaseOpened extends Event<memento.guide.aggregate.design.voidtype.PatientCase> {
    String patientId
    Date caseOpenedAt = new Date()
}
