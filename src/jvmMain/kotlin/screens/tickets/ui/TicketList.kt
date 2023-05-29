package screens.tickets.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import data.ticket.model.TicketLocal

@Composable
fun TicketList(
    ticketList: List<TicketLocal>,
    editTicket: (TicketLocal) -> Unit = {},
    deleteTicket: (TicketLocal) -> Unit = {}
) {
    LazyVerticalGrid(
        GridCells.Adaptive(300.dp),
        contentPadding = PaddingValues(4.dp)
    ){
        items(ticketList){
            TicketCard(
                ticket = it,
                editTicket,
                deleteTicket
            )
        }
    }
}