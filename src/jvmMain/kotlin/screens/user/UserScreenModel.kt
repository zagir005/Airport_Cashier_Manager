package screens.user

import common.BaseStateScreenModel
import data.users.datasource.UsersLocalCrudDataSource
import data.users.model.UserLocal

class UserScreenModel(
    private val usersLocalCrudDataSource: UsersLocalCrudDataSource
): BaseStateScreenModel<UserScreenState>(UserScreenState.Loading){
    init {
        loadUsers()
    }

    fun loadUsers(){
        launchIOCoroutine {
            updateState(
                UserScreenState.UsersListLoaded(usersLocalCrudDataSource.getAll())
            )
        }
    }

    fun addUser(userLocal: UserLocal){
        launchIOCoroutine {
            usersLocalCrudDataSource.add(userLocal)
            loadUsers()
        }
    }
    fun updateUser(userLocal: UserLocal){
        launchIOCoroutine {
            usersLocalCrudDataSource.update(userLocal)
            loadUsers()
        }
    }
    fun deleteUser(userLocal: UserLocal){
        launchIOCoroutine {
            usersLocalCrudDataSource.remove(userLocal)
            loadUsers()
        }
    }
}