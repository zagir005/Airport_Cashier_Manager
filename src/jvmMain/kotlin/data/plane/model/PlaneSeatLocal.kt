package data.plane.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class PlaneSeatLocal: RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var categoryName: String = ""
    var baggageWeight: Int = 0
    var seatInfo: RealmList<SeatInfoLocal> = realmListOf()


}
