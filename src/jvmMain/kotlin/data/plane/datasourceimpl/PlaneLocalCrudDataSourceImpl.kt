package data.plane.datasourceimpl

import data.AppDatabase
import data.plane.datasource.PlaneLocalCrudDataSource
import data.plane.model.PlaneLocal
import data.plane.model.PlaneSeatLocal
import io.realm.kotlin.ext.query
import org.mongodb.kbson.ObjectId


class PlaneLocalCrudDataSourceImpl(private val appDatabase: AppDatabase): PlaneLocalCrudDataSource {
    override suspend fun add(obj: PlaneLocal) {
        appDatabase.db.write {
            copyToRealm(obj)
        }
    }

    override suspend fun addAll(list: List<PlaneLocal>) {
        appDatabase.db.write {
            list.forEach {
                copyToRealm(it)
            }
        }
    }

    override suspend fun addSeat(planeObj: ObjectId, seat: PlaneSeatLocal) {
        appDatabase.db.write {
            this.query<PlaneLocal>().find().find {
                it.id == planeObj
            }!!.apply {
                seats.add(seat)
            }
        }
    }

    override suspend fun updateSeat(planeObj: ObjectId, seat: PlaneSeatLocal) {
        appDatabase.db.write {
            this.query<PlaneLocal>().find().find {
                it.id == planeObj
            }!!.apply {
                seats.find {
                    it.id == seat.id
                }?.apply {
                    baggageWeight = seat.baggageWeight
                    seatInfo = seat.seatInfo
                    categoryName = seat.categoryName
                }
            }
        }
    }

    override suspend fun removeSeat(planeObj: ObjectId, seat: PlaneSeatLocal) {
        appDatabase.db.write {
            this.query<PlaneLocal>().find().find {
                it.id == planeObj
            }!!.apply {
                seats.removeIf {
                    it.id == seat.id
                }
            }
        }
    }

    override suspend fun getPlaneSeatById(planeSeatId: ObjectId): PlaneSeatLocal? {
        return appDatabase.db.query<PlaneSeatLocal>("id == $0", planeSeatId).first().find()
    }


    override suspend fun getById(id: ObjectId): PlaneLocal? =
        appDatabase.db.query<PlaneLocal>("id == $0", id).first().find()


    override suspend fun getAll(): List<PlaneLocal> {
        return appDatabase.db.query<PlaneLocal>().find()
    }

    override suspend fun update(obj: PlaneLocal) {
        appDatabase.db.write {
            this.query<PlaneLocal>().find().find {
                it.id == obj.id
            }!!.apply {
                name = obj.name
                codeName = obj.codeName
            }
        }
    }


    override suspend fun removeById(id: ObjectId) {
        appDatabase.db.write {
            query<PlaneLocal>("id == $0",id).first().find()?.let {
                try {
                    delete(it)
                } catch (e: IllegalArgumentException) {
                    throw e
                }
            }
        }
    }

    override suspend fun remove(obj: PlaneLocal) {
        appDatabase.db.write {
            delete(findLatest(obj)!!)
        }
    }

}