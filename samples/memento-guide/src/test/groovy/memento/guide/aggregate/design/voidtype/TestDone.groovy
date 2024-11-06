package memento.guide.aggregate.design.voidtype

import groovy.transform.TupleConstructor
import memento.model.Event

@TupleConstructor
class TestDone extends Event<PatientCase> {
    String testApplied
}
