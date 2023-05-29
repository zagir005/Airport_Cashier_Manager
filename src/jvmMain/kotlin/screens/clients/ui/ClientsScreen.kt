package screens.clients.ui

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import data.client.model.ClientLocal

@Composable
fun ClientsList(
    clientList: List<ClientLocal>,
    editClient: (ClientLocal) -> Unit = {},
    deleteClient: (ClientLocal) -> Unit = {},
    clientClick: (ClientLocal) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(320.dp)
    ){
        items(clientList){client ->
            ClientCard(
                client = client,
                editClient = {
                    editClient(it)
                },
                deleteClient = {
                    deleteClient(it)
                },
                clientClick = {
                    clientClick(it)
                }
            )
        }
    }
}
