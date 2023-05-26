package data.plane.model

import data.plane.model.PlaneSeatLocal
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class PlaneLocal: RealmObject{
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var name: String = ""
    var codeName: String = ""
    var seats: RealmList<PlaneSeatLocal> = realmListOf()
}