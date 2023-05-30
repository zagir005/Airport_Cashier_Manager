package screens.user

import data.users.model.UserLocal

sealed class UserScreenState {
    object Loading: UserScreenState()
    class UsersListLoaded(val usersList: List<UserLocal>): UserScreenState()


}