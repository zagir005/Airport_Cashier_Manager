package screens.clients

import data.client.model.ClientLocal

sealed class ClientScreenState {
    object Loading: ClientScreenState()

    data class ClientListLoaded(
        val clientList: List<ClientLocal>
    ): ClientScreenState()
}