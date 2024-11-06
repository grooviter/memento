package memento.guide.aggregate.design.raw

import groovy.transform.TupleConstructor
import memento.guide.aggregate.design.voidtype.PatientCase
import memento.model.Event

@TupleConstructor
class TestDone extends Event<PatientCase> {
    String testApplied
}
