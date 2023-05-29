package data.users.datasource

import common.LocalCrudDataSource
import data.users.model.UserAuth
import data.users.model.UserLocal

interface UsersLocalCrudDataSource: LocalCrudDataSource<UserLocal>{
    suspend fun authUser(obj: UserAuth): UserLocal?

}