package screens.planes.details

import common.BaseStateScreenModel
import data.plane.datasource.PlaneLocalCrudDataSource
import data.plane.model.PlaneLocal
import data.plane.model.PlaneSeatLocal
import data.plane.model.SeatInfoLocal
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import org.mongodb.kbson.ObjectId
import screens.planes.model.PlaneSeatCategory
import kotlin.random.Random

class PlaneDetailsModel(
    private val planeLocalCrudDataSource: PlaneLocalCrudDataSource,
    private val planeId: ObjectId
): BaseStateScreenModel<PlaneDetailsState>(PlaneDetailsState.Loading) {

    init {
        updatePlane()
    }

    private fun updatePlane(){
        launchIOCoroutine {
            updateState(
                PlaneDetailsState.PlaneIsLoaded(
                    planeLocalCrudDataSource.getById(planeId)!!
                )
            )
        }
    }

    fun removePlaneSeats(planeSeatCategory: PlaneSeatCategory){
        launchIOCoroutine {
            planeLocalCrudDataSource.removeSeat(
                planeId,
                planeLocalCrudDataSource.getPlaneSeatById(planeSeatCategory.seatId)!!
            )
            updatePlane()
        }
    }

    fun addSeatCategory(planeSeatCategory: PlaneSeatCategory){
        launchIOCoroutine {
            planeLocalCrudDataSource.addSeat(
                planeId,
                PlaneSeatLocal().apply {
                    id = planeSeatCategory.seatId
                    categoryName = planeSeatCategory.categoryName
                    baggageWeight = planeSeatCategory.baggageWeight
                    seatInfo = List(planeSeatCategory.count){
                        SeatInfoLocal().apply {
                            row = it
                            column = Random.nextInt(6)
                        }
                    }.toRealmList()
                }
            )
            updatePlane()
        }
    }

    fun updatePlaneSeat(planeSeatCategory: PlaneSeatCategory){
        launchIOCoroutine {
            planeLocalCrudDataSource.updateSeat(
                planeId,
                PlaneSeatLocal().apply {
                    id = planeSeatCategory.seatId
                    categoryName = planeSeatCategory.categoryName
                    baggageWeight = planeSeatCategory.baggageWeight
                    seatInfo = List(planeSeatCategory.count){
                        SeatInfoLocal().apply {
                            row = it
                            column = Random.nextInt(6)
                        }
                    }.toRealmList()
                }
            )
            updatePlane()
        }
    }

    companion object{
        fun getPlaneSeatsCategories(planeLocal: PlaneLocal): List<PlaneSeatCategory> {
            return planeLocal.seats.map { planeSeat ->
                PlaneSeatCategory(
                    planeId = planeLocal.id,
                    seatId = planeSeat.id,
                    categoryName = planeSeat.categoryName,
                    count = planeSeat.seatInfo.count(),
                    baggageWeight = planeSeat.baggageWeight
                )
            }
        }
    }
}