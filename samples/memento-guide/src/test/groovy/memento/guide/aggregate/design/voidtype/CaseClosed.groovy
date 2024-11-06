package memento.guide.aggregate.design.voidtype

import memento.model.Event

class CaseClosed extends Event<PatientCase> {
    Date caseClosedAt = new Date()
}
