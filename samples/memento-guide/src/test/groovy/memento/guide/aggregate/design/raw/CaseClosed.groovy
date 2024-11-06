package memento.guide.aggregate.design.raw

import memento.guide.aggregate.design.voidtype.PatientCase
import memento.model.Event

class CaseClosed extends Event<PatientCase> {
    Date caseClosedAt = new Date()
}
