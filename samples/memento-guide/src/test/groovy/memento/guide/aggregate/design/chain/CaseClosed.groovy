package memento.guide.aggregate.design.chain

import memento.model.Event

class CaseClosed extends Event<PatientCase> {
    Date caseClosedAt = new Date()
}
