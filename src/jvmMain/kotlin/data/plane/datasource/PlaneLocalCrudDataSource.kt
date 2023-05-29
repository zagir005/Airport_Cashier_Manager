package data.plane.datasource

import common.LocalCrudDataSource
import data.plane.model.PlaneLocal
import data.plane.model.PlaneSeatLocal
import org.mongodb.kbson.ObjectId

interface PlaneLocalCrudDataSource: LocalCrudDataSource<PlaneLocal>{
    suspend fun addSeat(planeObj: ObjectId, seat: PlaneSeatLocal)
    suspend fun updateSeat(planeObj: ObjectId, seat: PlaneSeatLocal)
    suspend fun removeSeat(planeObj: ObjectId, seat: PlaneSeatLocal)
    suspend fun getPlaneSeatById(planeSeatId: ObjectId): PlaneSeatLocal?
}