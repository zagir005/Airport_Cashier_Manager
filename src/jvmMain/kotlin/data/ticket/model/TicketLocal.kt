package data.ticket.model

import data.client.model.ClientLocal
import data.flight.model.FlightLocal
import data.flight.model.TicketPrice
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class TicketLocal: RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var ticketNumber: String = ""
    var client: ClientLocal? = null
    var flight: FlightLocal? = null
    var ticketPrice: TicketPrice? = null
}