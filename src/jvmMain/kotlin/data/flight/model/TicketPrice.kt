package data.flight.model

import data.plane.model.PlaneSeatLocal
import io.realm.kotlin.types.EmbeddedRealmObject

class TicketPrice: EmbeddedRealmObject {
    var planeSeatLocal: PlaneSeatLocal? = null
    var price: Int = 0
}