package data.ticket.datasource

import common.LocalCrudDataSource
import data.client.model.ClientLocal
import data.flight.model.FlightLocal
import data.ticket.model.TicketLocal

interface TicketLocalCrudDataSource: LocalCrudDataSource<TicketLocal>{

    suspend fun getAllFlight(): List<FlightLocal>
    suspend fun getAllClient(): List<ClientLocal>

}