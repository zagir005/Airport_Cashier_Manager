package screens.planes.list

import data.plane.model.PlaneLocal

sealed class PlaneScreenState{
    object Loading: PlaneScreenState()
    data class PlanesListLoaded(val planeList: List<PlaneLocal>): PlaneScreenState()
}