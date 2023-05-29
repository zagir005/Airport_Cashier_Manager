package screens.tickets

import common.BaseStateScreenModel
import data.ticket.datasource.TicketLocalCrudDataSource
import data.ticket.model.TicketLocal

class TicketScreenModel(
    private val ticketLocalCrudDataSource: TicketLocalCrudDataSource
): BaseStateScreenModel<TicketScreenState>(TicketScreenState.Loading){

    init {
        loadTickets()
    }
    fun loadTickets(){
        launchIOCoroutine {
            updateState(
                TicketScreenState.TicketsListLoaded(
                    ticketLocalCrudDataSource.getAll().apply {

                    },
                    ticketLocalCrudDataSource.getAllClient().apply {
                        println(this)
                    },
                    ticketLocalCrudDataSource.getAllFlight().apply {
                        println(this)
                    }
                ),
            )
        }
    }

    fun addTicket(ticketLocal: TicketLocal){
        launchIOCoroutine {
            ticketLocalCrudDataSource.add(ticketLocal)
            loadTickets()
        }
    }

    fun updateTicket(ticketLocal: TicketLocal){
        launchIOCoroutine {
            ticketLocalCrudDataSource.update(ticketLocal)
            loadTickets()
        }
    }

    fun deleteTicket(ticketLocal: TicketLocal){
        launchIOCoroutine {
            ticketLocalCrudDataSource.remove(ticketLocal)
            loadTickets()
        }
    }

}