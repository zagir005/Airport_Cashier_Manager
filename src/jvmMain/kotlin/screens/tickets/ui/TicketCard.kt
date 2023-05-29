package screens.tickets.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.ticket.model.TicketLocal
import ui.LabelText
import ui.card.CardTitleText
import ui.card.head.EditDeleteHeadCard

@Composable
fun TicketCard(
    ticket: TicketLocal,
    editTicket: (TicketLocal) -> Unit = {},
    deleteTicket: (TicketLocal) -> Unit = {},
) {
    EditDeleteHeadCard(
        title = {
            CardTitleText(
                text = "Билет ${ticket.ticketNumber}",
                rowScope = this,
                textModifier = {
                    it.padding(start = 4.dp)
                }
            )
        },
        editBtnClick = {
            editTicket(ticket)
        },
        deleteBtnClick = {
            deleteTicket(ticket)
        },
        cardModifier = Modifier.heightIn(120.dp,500.dp).padding(8.dp)
    ){
        Box(
            modifier = Modifier.padding(8.dp)
        ) {

            Column {
                LabelText("Рейс: ", ticket.flight?.flightNumber!!, style = MaterialTheme.typography.bodyLarge)
                LabelText("Клиент: ", "${ticket.client?.lastName} ${ticket.client?.firstName!!.first()}. ${ticket.client?.patronymic!!.first()}.", style = MaterialTheme.typography.bodyLarge)
                LabelText("Тариф: ", ticket.ticketPrice?.planeSeatLocal!!.categoryName, style = MaterialTheme.typography.bodyLarge)
                LabelText("Цена: ", ticket.ticketPrice?.price.toString(), style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}