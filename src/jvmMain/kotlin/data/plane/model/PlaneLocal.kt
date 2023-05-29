package data.plane.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import java.util.*

class PlaneLocal: RealmObject{
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var name: String = ""
    var codeName: String = ""
    var seats: RealmList<PlaneSeatLocal> = realmListOf()

    companion object {
        fun generatePlanes(count: Int): RealmList<PlaneLocal> {
            val planes = realmListOf<PlaneLocal>()

            for (i in 1..count) {
                val plane = PlaneLocal()
                plane.name = "Boeing " + (737 + Random().nextInt(3) * 100)
                plane.codeName = "BN" + (10 + i)
                plane.seats = PlaneSeatLocal.generateSeats()

                planes.add(plane)
            }

            return planes
        }
    }
}