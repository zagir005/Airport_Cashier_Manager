package screens.tickets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import data.AppDatabase
import data.ticket.datasourceimpl.TicketLocalCrudDataSourceImpl
import data.ticket.model.TicketLocal
import screens.tickets.ui.TicketEditDialog
import screens.tickets.ui.TicketList
import ui.ToolPanel

object TicketsTabScreen: Tab {
    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 0u,
            title = "Билеты",
            icon = painterResource("ticket.svg")
        )

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { TicketScreenModel(TicketLocalCrudDataSourceImpl(AppDatabase())) }
        val screenState by screenModel.state.collectAsState()

        LaunchedEffect(Unit){
            screenModel.loadTickets()
        }

        var isTicketEditDialogIsOpen by remember { mutableStateOf(false) }
        var editableTicketLocal by remember { mutableStateOf<TicketLocal?>(null) }

        if (isTicketEditDialogIsOpen && screenState is TicketScreenState.TicketsListLoaded){
            TicketEditDialog(
                isOpen = isTicketEditDialogIsOpen,
                ticketLocal = editableTicketLocal,
                clientList = (screenState as TicketScreenState.TicketsListLoaded).clientList,
                flightList = (screenState as TicketScreenState.TicketsListLoaded).flightList,
                update = {
                    screenModel.updateTicket(it)
                },
                add = {
                    screenModel.addTicket(it)
                },
                onClose = {
                    isTicketEditDialogIsOpen = false
                    editableTicketLocal = null
                }
            )
        }

        Column {
            ToolPanel(
                searchCall = {

                },
                filterBtnClick = {

                },
                addBtnClick = {
                    println("ADD CALL")
                    editableTicketLocal = null
                    isTicketEditDialogIsOpen = true
                }
            )
            when(val state = screenState){
                TicketScreenState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize()){
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
                is TicketScreenState.TicketsListLoaded -> {
                    TicketList(
                        state.ticketsList,
                        deleteTicket = {
                            screenModel.deleteTicket(it)
                        },
                        editTicket = {
                            editableTicketLocal = it
                            isTicketEditDialogIsOpen = true
                        }
                    )
                    state.ticketsList
                }
            }
        }
    }

}