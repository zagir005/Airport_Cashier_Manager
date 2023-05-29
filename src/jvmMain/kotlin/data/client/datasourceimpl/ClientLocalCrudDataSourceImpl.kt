package data.client.datasourceimpl

import data.AppDatabase
import data.client.datasource.ClientLocalCrudDataSource
import data.client.model.ClientLocal
import data.ticket.model.TicketLocal
import io.realm.kotlin.ext.query
import org.mongodb.kbson.ObjectId

class ClientLocalCrudDataSourceImpl(private val database: AppDatabase): ClientLocalCrudDataSource {
    override suspend fun getById(id: ObjectId): ClientLocal? =
        database.db.query<ClientLocal>("id == $0", id).first().find()


    override suspend fun getAll(): List<ClientLocal> =
        database.db.query<ClientLocal>().find()


    override suspend fun add(obj: ClientLocal) {
        database.db.write {
            copyToRealm(obj)
        }
    }

    override suspend fun addAll(list: List<ClientLocal>) {
        database.db.write {
            list.forEach {
                copyToRealm(it)
            }
        }
    }

    override suspend fun update(obj: ClientLocal) {
        database.db.write {
            query<ClientLocal>("id == $0", obj.id).first().find()?.apply {
                firstName = obj.firstName
                lastName = obj.lastName
                patronymic = obj.patronymic
                passportNumber = obj.passportNumber
                birthday = obj.birthday
                gender = obj.gender
            }
        }
    }

    override suspend fun removeById(id: ObjectId) {
        database.db.write {
            query<TicketLocal>().find().filter {
                it.client?.id == id
            }.forEach {
                delete(it)
            }
            delete(
                query<ClientLocal>("id == $0", id)
                    .first()
                    .find()!!
            )
        }
    }

    override suspend fun remove(obj: ClientLocal) {
        database.db.write {
            delete(findLatest(obj)!!)
        }
    }
}