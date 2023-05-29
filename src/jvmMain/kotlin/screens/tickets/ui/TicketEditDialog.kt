package screens.tickets.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import common.getInitialsName
import data.client.model.ClientLocal
import data.flight.model.FlightLocal
import data.ticket.model.TicketLocal
import ui.LabelText
import ui.SaveOrCancelButtons
import ui.icon.errorIcon
import java.awt.Dimension

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketEditDialog(
    ticketLocal: TicketLocal?,
    flightList: List<FlightLocal>,
    clientList: List<ClientLocal>,
    isOpen: Boolean,
    update: (TicketLocal) -> Unit = {},
    add: (TicketLocal) -> Unit = {},
    onClose: () -> Unit,
) {
    val isNewTicket = ticketLocal == null
    Dialog(
        visible = isOpen,
        onCloseRequest = {
            onClose()
        },
        title = if (isNewTicket) "Добавить новый билет" else "Редактировать билет ${ticketLocal?.flight!!.flightNumber}"
    ){
        window.minimumSize = Dimension(300, 400)
        var editableTicket = if (isNewTicket){
            TicketLocal()
        }else{
            TicketLocal().apply{
                id = ticketLocal!!.id
                flight = ticketLocal.flight
                client = ticketLocal.client
                ticketNumber = ticketLocal.ticketNumber
                ticketPrice = ticketLocal.ticketPrice
            }
        }
        var ticketNumber by remember { mutableStateOf(editableTicket.ticketNumber) }

        var client by remember{ mutableStateOf(editableTicket.client) }
        var flight by remember{ mutableStateOf(editableTicket.flight) }
        var ticketPrice by remember{ mutableStateOf(editableTicket.ticketPrice) }

        var clientName by remember{ mutableStateOf(editableTicket.client?.getInitialsName() ?: "") }
        var flightNumber by remember{ mutableStateOf(editableTicket.flight?.flightNumber ?: "") }
        var ticketPriceSeatCategory by remember{ mutableStateOf(editableTicket.ticketPrice?.planeSeatLocal?.categoryName ?: "") }

        var clientDropDownMenuExpanded by remember { mutableStateOf(false) }
        var flightDropDownMenuExpanded by remember { mutableStateOf(false) }
        var ticketPriceDropDownMenuExpanded by remember { mutableStateOf(false) }

        var ticketNumberIsEmptyError by remember{ mutableStateOf(false) }
        var clientNotSelectedError by remember{ mutableStateOf(false) }
        var flightNotSelectedError by remember{ mutableStateOf(false) }
        var ticketPriceNotSelectedError by remember{ mutableStateOf(false) }



        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
        ) {

            TextField(
                value = ticketNumber,
                onValueChange = {
                    ticketNumber = it
                },
                label = {
                    Text("Номер билета")
                },
                supportingText = {
                    if (ticketNumberIsEmptyError) {
                        Text("Поле должно быть заполнено")
                    }
                },
                trailingIcon = {
                    if (ticketNumberIsEmptyError) {
                        errorIcon()
                    }
                },
                isError = ticketNumberIsEmptyError,
                modifier = Modifier.fillMaxWidth()
            )

            Box {
                DropdownMenu(
                    expanded = clientDropDownMenuExpanded,
                    onDismissRequest = {
                        clientDropDownMenuExpanded = false
                    }
                ){
                    for (i in clientList){
                        DropdownMenuItem(
                            onClick = {
                                clientDropDownMenuExpanded = false
                                client = i
                                clientName = i.getInitialsName()
                            },
                            text = {
                                Text(i.getInitialsName())
                            }
                        )
                    }
                }
                TextField(
                    value = clientName,
                    onValueChange = {  },
                    label = { Text("Клиент") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                clientDropDownMenuExpanded = !clientDropDownMenuExpanded
                            }
                        ){
                            Icon(Icons.Default.ArrowDropDown,"")
                        }
                    }
                )
            }
            Box {
                DropdownMenu(
                    expanded = flightDropDownMenuExpanded,
                    onDismissRequest = {
                        flightDropDownMenuExpanded = false
                    }
                ){
                    for (i in flightList){
                        DropdownMenuItem(
                            onClick = {
                                flightDropDownMenuExpanded = false
                                flight = i
                                flightNumber = i.flightNumber
                            },
                            text = {
                                Text(i.flightNumber)
                            }
                        )
                    }
                }
                TextField(
                    value = flightNumber,
                    onValueChange = {  },
                    label = { Text("Рейс") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                flightDropDownMenuExpanded = !flightDropDownMenuExpanded
                            }
                        ){
                            Icon(Icons.Default.ArrowDropDown,"")
                        }
                    }
                )
            }
            Box {
                DropdownMenu(
                    expanded = ticketPriceDropDownMenuExpanded,
                    onDismissRequest = {
                        ticketPriceDropDownMenuExpanded = false
                    }
                ){
                    if (flight != null){

                        for (i in flight?.ticketPriceList!!){
                            DropdownMenuItem(
                                onClick = {
                                    ticketPriceDropDownMenuExpanded = false
                                    ticketPrice = i
                                    ticketPriceSeatCategory = i.planeSeatLocal?.categoryName!!
                                },
                                text = {
                                    Text(i.planeSeatLocal?.categoryName!!)
                                }
                            )
                        }
                    }
                }
                TextField(
                    value = ticketPriceSeatCategory,
                    onValueChange = {  },
                    label = { Text("Тариф") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                ticketPriceDropDownMenuExpanded = !ticketPriceDropDownMenuExpanded
                            }
                        ){
                            Icon(Icons.Default.ArrowDropDown,"")
                        }
                    }
                )


            }
            Spacer(modifier = Modifier.height(4.dp))

            LabelText(label = "Цена билета: ", if (ticketPrice == null) "..." else ticketPrice?.price.toString() + " р.")

            SaveOrCancelButtons(
                cancelBtnClick = {
                    onClose()
                },
                saveBtnClick = {
                    editableTicket = TicketLocal().apply{
                        this.ticketNumber = ticketNumber
                        this.ticketPrice = ticketPrice
                        this.client = client
                        this.flight = flight
                        this.id = editableTicket.id
                    }
                    println(editableTicket.flight)
                    ticketNumberIsEmptyError = ticketNumber.isEmpty()
                    clientNotSelectedError = client == null
                    flightNotSelectedError = flight == null
                    ticketPriceNotSelectedError = ticketPrice == null
                    if (
                        !ticketNumberIsEmptyError &&
                        !clientNotSelectedError &&
                        !flightNotSelectedError &&
                        !ticketPriceNotSelectedError
                    ){
                        if (isNewTicket){
                            add(editableTicket)
                        }else{
                            update(editableTicket)
                        }
                        onClose()
                    }
                }
            )
        }
    }
}