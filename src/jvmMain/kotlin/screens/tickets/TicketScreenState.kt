package screens.tickets

import data.client.model.ClientLocal
import data.flight.model.FlightLocal
import data.ticket.model.TicketLocal

sealed class TicketScreenState{
    object Loading: TicketScreenState()

    class TicketsListLoaded(
        val ticketsList: List<TicketLocal>,
        val clientList: List<ClientLocal>,
        val flightList: List<FlightLocal>,
    ): TicketScreenState()
}
