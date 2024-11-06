package memento.guide.aggregate.design.chain

import groovy.transform.TupleConstructor
import memento.model.Event

@TupleConstructor
class TestDone extends Event<PatientCase> {
    String testApplied
}
