package data.plane.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import java.util.*

class PlaneSeatLocal: RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var categoryName: String = ""
    var baggageWeight: Int = 0
    var seatInfo: RealmList<SeatInfoLocal> = realmListOf()

    companion object {
        private val tariffNames = listOf("Эконом", "Комфорт", "Бизнес", "Первый", "Премиум")

        fun generateSeats(tariffCount: Int): RealmList<PlaneSeatLocal> {
            val seats = realmListOf<PlaneSeatLocal>()
            val random = Random()

            for (i in 1..10) {
                val seat = PlaneSeatLocal()
                seat.baggageWeight = 10 + random.nextInt(20)

                val uniqueTariffs = mutableSetOf<String>()
                while (uniqueTariffs.size < tariffCount) {
                    uniqueTariffs.add(tariffNames.random())
                }

                for (tariffName in uniqueTariffs) {
                    val info = SeatInfoLocal()
                    info.row = i
                    info.column = random.nextInt(30) + 1
                    seat.seatInfo.add(info)
                }

                seats.add(seat)
            }
            return seats
        }
    }
}
