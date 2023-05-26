package screens.planes.list

import cafe.adriel.voyager.core.model.coroutineScope
import common.BaseStateScreenModel
import data.plane.datasource.PlaneLocalCrudDataSource
import data.plane.model.PlaneLocal
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PlaneScreenModel(
    private val planeDataSource: PlaneLocalCrudDataSource
): BaseStateScreenModel<PlaneScreenState>(PlaneScreenState.Loading) {

    private var planesList: List<PlaneLocal> = mutableListOf()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        launchIOCoroutine {
            planesList = planeDataSource.getAll()
            updateState(
                PlaneScreenState.PlanesListLoaded(planesList)
            )
        }
    }

    fun planeSearch(query: String) {
        if (query.isNotEmpty()) {
            updateState(PlaneScreenState.PlanesListLoaded(
                planesList.filter {
                    it.name.contains(query, ignoreCase = true)
                }.ifEmpty {
                    planesList.filter {
                        it.codeName.contains(query, ignoreCase = true)
                    }
                }
            ))
        } else {
            loadUsers()
        }
    }

    fun updatePlane(plane: PlaneLocal) {
        launchIOCoroutine {
            planeDataSource.update(plane)
            loadUsers()
        }
    }

    fun addPlane(plane: PlaneLocal) {
        launchIOCoroutine {
            planeDataSource.add(plane)
            loadUsers()
        }
    }

    fun deletePlane(plane: PlaneLocal) {
        launchIOCoroutine {
            planeDataSource.remove(plane)
            loadUsers()
        }
    }

    companion object{
        fun getPlaneCategories(planeLocal: PlaneLocal): Map<String, Int> {
            val seatsCategories = mutableMapOf<String, Int>()
            planeLocal.seats.forEach {
                seatsCategories[it.categoryName] = it.seatInfo.count()
            }
            return seatsCategories
        }

        fun getCategoriesOfPlanes(list: List<PlaneLocal>): List<Map<String, Int>>{
            return list.map {
                getPlaneCategories(it)
            }
        }
    }
}