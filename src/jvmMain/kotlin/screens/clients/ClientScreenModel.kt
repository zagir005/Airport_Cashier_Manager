package screens.clients

import common.BaseStateScreenModel
import data.client.datasource.ClientLocalCrudDataSource
import data.client.model.ClientLocal
import org.mongodb.kbson.ObjectId

class ClientScreenModel(
    private val clientLocalCrudDataSource: ClientLocalCrudDataSource
): BaseStateScreenModel<ClientScreenState>(ClientScreenState.Loading) {
    init{
            loadClients()
    }

    fun loadClients(){
        launchIOCoroutine {
            mutableState.value = ClientScreenState.ClientListLoaded(
                clientLocalCrudDataSource.getAll()
            )
        }
    }

    fun updateClient(obj: ClientLocal){
        launchIOCoroutine {
            clientLocalCrudDataSource.update(obj)
            loadClients()
        }
    }

    fun addClient(obj: ClientLocal){
        launchIOCoroutine {
            clientLocalCrudDataSource.add(obj)
            loadClients()
        }
    }
    fun deleteClient(id: ObjectId){
        launchIOCoroutine {
            clientLocalCrudDataSource.removeById(id)
            loadClients()
        }
    }
}