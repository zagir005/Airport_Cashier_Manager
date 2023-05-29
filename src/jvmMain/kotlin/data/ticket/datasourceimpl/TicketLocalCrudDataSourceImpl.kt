package data.ticket.datasourceimpl

import data.AppDatabase
import data.client.model.ClientLocal
import data.flight.model.FlightLocal
import data.ticket.datasource.TicketLocalCrudDataSource
import data.ticket.model.TicketLocal
import io.realm.kotlin.ext.query
import org.mongodb.kbson.ObjectId

class TicketLocalCrudDataSourceImpl(private val database: AppDatabase): TicketLocalCrudDataSource {


    override suspend fun getById(id: ObjectId): TicketLocal? =
        database.db.query<TicketLocal>("id == $0", id).first().find()

    override suspend fun getAll(): List<TicketLocal> =
        database.db.query<TicketLocal>().find()

    override suspend fun add(obj: TicketLocal) {
        database.db.write {
            println(obj.flight)
            val flight = query<FlightLocal>("id == $0", obj.flight?.id).first().find()!!
            println(flight)
            val client = query<ClientLocal>("id == $0", obj.client?.id).first().find()!!
            copyToRealm(
                obj.apply {
                    this.flight = flight
                    this.client = client
                    this.ticketPrice = flight.ticketPriceList.find {
                        it.planeSeatLocal?.categoryName == obj.ticketPrice?.planeSeatLocal?.categoryName
                    }
                }
            )
        }
    }

    override suspend fun addAll(list: List<TicketLocal>) {
        database.db.write {
            list.forEach {
                copyToRealm(it)
            }
        }
    }

    override suspend fun update(obj: TicketLocal) {
        database.db.write {
            println(obj.flight)
            val flight = query<FlightLocal>("id == $0", obj.flight?.id).first().find()!!
            println(flight)
            val client = query<ClientLocal>("id == $0", obj.client?.id).first().find()!!
            query<TicketLocal>("id == $0", obj.id).first().find()!!.apply {
                this.client = client
                this.flight = flight
                this.ticketPrice = flight.ticketPriceList.find {
                    it.planeSeatLocal?.categoryName == obj.ticketPrice?.planeSeatLocal?.categoryName
                }
                this.ticketNumber = obj.ticketNumber
            }
        }
    }

    override suspend fun getAllFlight(): List<FlightLocal> =
        database.db.query<FlightLocal>().find()

    override suspend fun getAllClient(): List<ClientLocal> =
        database.db.query<ClientLocal>().find()

    override suspend fun removeById(id: ObjectId) =
        database.db.write {
            delete(
                query<TicketLocal>("id == $0", id).first().find()!!
            )
        }

    override suspend fun remove(obj: TicketLocal) =
        database.db.write {
            delete(
                query<TicketLocal>("id == $0", obj.id).first().find()!!
            )
        }
}