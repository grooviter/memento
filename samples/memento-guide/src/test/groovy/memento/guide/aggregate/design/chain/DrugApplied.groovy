package memento.guide.aggregate.design.chain

import groovy.transform.TupleConstructor
import memento.model.Event

@TupleConstructor
class DrugApplied extends Event<PatientCase> {
    String prescribedDrug
}
