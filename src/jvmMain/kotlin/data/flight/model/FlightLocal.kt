package data.flight.model

import data.airport.model.AirportLocal
import data.plane.model.PlaneLocal
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class FlightLocal(): RealmObject {
    @PrimaryKey
    var id: ObjectId = BsonObjectId()
    var flightNumber: String = ""
    var plane: PlaneLocal? = null
    var company: String = ""
    var departureDate: Long = 0
    var arrivalDate: Long = 0
    var departureAirport: AirportLocal? = null
    var arrivalAirport: AirportLocal? = null
    var ticketPriceList: RealmList<TicketPrice> = if (plane != null){
        plane!!.seats.map {
            TicketPrice().apply {
                planeSeatLocal = it
                price = 0
            }
        }.toRealmList()
    }else{
        realmListOf()
    }
}