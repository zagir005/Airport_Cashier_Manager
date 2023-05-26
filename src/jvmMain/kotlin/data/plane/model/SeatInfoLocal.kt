package data.plane.model

import io.realm.kotlin.types.EmbeddedRealmObject

class SeatInfoLocal: EmbeddedRealmObject {
    var row: Int = 0
    var column: Int = 0
}