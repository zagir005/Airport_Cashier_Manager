package data.airport.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class AirportLocal(): RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var cityCode: String = ""
    var name: String = ""
    var city: String = ""
}