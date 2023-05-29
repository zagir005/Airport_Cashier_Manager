package data.flight.datasourceimpl

import data.AppDatabase
import data.airport.model.AirportLocal
import data.flight.datasource.FlightLocalCrudDataSource
import data.flight.model.FlightLocal
import data.flight.model.TicketPrice
import data.plane.model.PlaneLocal
import data.ticket.model.TicketLocal
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.toRealmList
import org.mongodb.kbson.ObjectId

class FlightLocalCrudDataSourceImpl(
    private val database: AppDatabase
    ): FlightLocalCrudDataSource {
    override suspend fun getAllPlanes(): List<PlaneLocal> =
        database.db.query<PlaneLocal>().find()

    override suspend fun getAllAirports(): List<AirportLocal> =
        database.db.query<AirportLocal>().find()

    override suspend fun getById(id: ObjectId): FlightLocal? =
        database.db.query<FlightLocal>("id == $0", id).first().find()

    override suspend fun getAll(): List<FlightLocal> =
        database.db.query<FlightLocal>().find()

    override suspend fun add(obj: FlightLocal) {
        database.db.write {
            val plane = query<PlaneLocal>("id == $0", obj.plane?.id).first().find()!!
            val departureAirport = query<AirportLocal>("id == $0", obj.departureAirport?.id).first().find()!!
            val arrivalAirport = query<AirportLocal>("id == $0", obj.arrivalAirport?.id).first().find()!!
            copyToRealm(obj.apply {
                this.plane = plane
                this.departureAirport = departureAirport
                this.arrivalAirport = arrivalAirport
                println(ticketPriceList.joinToString {
                    it.planeSeatLocal?.id.toString()
                })
                this.ticketPriceList = plane.seats.map {
                    TicketPrice().apply {
                        planeSeatLocal = it
                        price = obj.ticketPriceList.find {ticketPrice ->
                            ticketPrice.planeSeatLocal?.id == it.id
                        }?.price!!
                    }
                }.toRealmList()
            })
        }
    }

    override suspend fun addAll(list: List<FlightLocal>) {
        database.db.write {
            list.map {
                copyToRealm(it)
            }
        }
    }

    override suspend fun update(obj: FlightLocal) {
        database.db.write {
            val plane = query<PlaneLocal>("id == $0", obj.plane?.id).first().find()!!
            val departureAirport = query<AirportLocal>("id == $0", obj.departureAirport?.id).first().find()!!
            val arrivalAirport = query<AirportLocal>("id == $0", obj.arrivalAirport?.id).first().find()!!
            query<FlightLocal>("id == $0", obj.id).first().find()!!.apply {
                this.flightNumber = obj.flightNumber
                this.company = obj.company
                this.departureDate = obj.departureDate
                this.arrivalDate = obj.arrivalDate
                this.plane = plane
                this.departureAirport = departureAirport
                this.arrivalAirport = arrivalAirport
                this.ticketPriceList = plane.seats.map {
                    TicketPrice().apply {
                        planeSeatLocal = it
                        price = obj.ticketPriceList.find {ticketPrice ->
                            ticketPrice.planeSeatLocal?.id == it.id
                        }?.price!!
                    }
                }.toRealmList()
            }
        }
    }

    override suspend fun removeById(id: ObjectId) {
        database.db.write{
            query<TicketLocal>().find().filter {
                it.flight?.id == id
            }.forEach {
                delete(it)
            }
            delete(
                query<FlightLocal>("id == $0", id).first().find()!!
            )
        }
    }

    override suspend fun remove(obj: FlightLocal) {
        database.db.write {
            delete(
                query<FlightLocal>("id == $0", obj.id).first().find()!!
            )
        }
    }
}