package screens.clients

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
import data.client.datasourceimpl.ClientLocalCrudDataSourceImpl
import data.client.model.ClientLocal
import screens.clients.ui.ClientInfoDialog
import screens.clients.ui.ClientsList
import ui.ToolPanel


object ClientsTabScreen: Tab{
    override val options: TabOptions
        @Composable
        get() =
            TabOptions(
                index = 0u,
                "Клиенты",
                painterResource("client.svg")
            )

    @Composable
    override fun Content() {
        val clientScreenModel = rememberScreenModel { ClientScreenModel(ClientLocalCrudDataSourceImpl(AppDatabase())) }
        val clientScreenState by clientScreenModel.state.collectAsState()

        LaunchedEffect(Unit){
            clientScreenModel.loadClients()
        }

        var editableClient by remember { mutableStateOf<ClientLocal?>(null) }
        var editClientDialogIsVisible by remember { mutableStateOf(false) }

        if (editClientDialogIsVisible){
            ClientInfoDialog(
                client = editableClient,
                onCloseRequest = {
                    editClientDialogIsVisible = false
                    editableClient = null
                },
                updateClient = {
                    clientScreenModel.updateClient(it)
                },
                addClient = {
                    clientScreenModel.addClient(it)
                }
            )
        }

        Column {
            ToolPanel(
                addBtnClick = {
                    editableClient = null
                    editClientDialogIsVisible = true
                },
                searchCall = {search ->

                },
                filterBtnClick = {

                }
            )
            when(val state = clientScreenState){
                ClientScreenState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize()){
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
                is ClientScreenState.ClientListLoaded -> {
                    ClientsList(
                        state.clientList,
                        editClient = {
                            editClientDialogIsVisible = true
                            editableClient = it
                        },
                        deleteClient = {
                            clientScreenModel.deleteClient(it.id)
                        },
                        clientClick = {

                        }
                    )
                }
            }
        }
    }
}