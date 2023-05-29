package data.airport.datasoureimpl

import data.AppDatabase
import data.airport.datasource.AirportLocalCrudDataSource
import data.airport.model.AirportLocal
import io.realm.kotlin.ext.query
import org.mongodb.kbson.ObjectId

class AirportLocalCrudDataSourceImpl(private val database: AppDatabase): AirportLocalCrudDataSource {
    override suspend fun getById(id: ObjectId): AirportLocal? =
        database.db.query<AirportLocal>("id == $0", id).first().find()


    override suspend fun getAll(): List<AirportLocal> =
        database.db.query<AirportLocal>().find()

    override suspend fun add(obj: AirportLocal) {
        database.db.write {
            copyToRealm(obj)
        }
    }

    override suspend fun addAll(list: List<AirportLocal>) {
        database.db.write {
            list.forEach {
                copyToRealm(it)
            }
        }
    }

    override suspend fun update(obj: AirportLocal) {
        database.db.write {
            query<AirportLocal>("id == $0", obj.id).first().find()?.apply {
                city = obj.city
                cityCode = obj.cityCode
                name = obj.name
            }
        }
    }

    override suspend fun removeById(id: ObjectId) {
        database.db.write {
            query<AirportLocal>("id == $0", id).first().find()?.let {
                delete(it)
            }
        }
    }

    override suspend fun remove(obj: AirportLocal) {
        database.db.write {
            delete(findLatest(obj)!!)
        }
    }
}