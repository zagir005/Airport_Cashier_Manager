package data.users.datasourceimpl

import data.AppDatabase
import data.users.datasource.UsersLocalCrudDataSource
import data.users.model.UserAuth
import data.users.model.UserLocal
import io.realm.kotlin.ext.query
import org.mongodb.kbson.ObjectId

class UsersLocalCrudDataSourceImpl(
    private val database: AppDatabase
): UsersLocalCrudDataSource {
    override suspend fun authUser(obj: UserAuth): UserLocal? {
        println(database.db.query<UserLocal>().find())
        return database.db.query<UserLocal>().find().find {
            it.userLogin == obj.login && it.password == obj.password
        }
    }

    override suspend fun getById(id: ObjectId): UserLocal? =
        database.db.query<UserLocal>("id == $0", id).first().find()

    override suspend fun getAll(): List<UserLocal> =
        database.db.query<UserLocal>().find()


    override suspend fun add(obj: UserLocal) {
        database.db.write {
            copyToRealm(obj)
        }
    }

    override suspend fun addAll(list: List<UserLocal>) {
        database.db.write {
            list.forEach {
                copyToRealm(it)
            }
        }
    }

    override suspend fun update(obj: UserLocal) {
        database.db.write {
            query<UserLocal>("id == $0", obj.id).first().find()?.apply {
                userLogin = obj.userLogin
                firstName = obj.firstName
                lastName = obj.lastName
                patronymic = obj.patronymic
                isAdmin = obj.isAdmin
                password = obj.password
            }
        }
    }

    override suspend fun removeById(id: ObjectId) {
        database.db.write {
            query<UserLocal>("id == $0", id).first().find()?.let {
                delete(it)
            }
        }
    }

    override suspend fun remove(obj: UserLocal) {
        database.db.write {
            delete(findLatest(obj)!!)
        }
    }
}


