package screens.planes.details

import data.plane.model.PlaneLocal

sealed class PlaneDetailsState {
    object Loading: PlaneDetailsState()

    data class PlaneIsLoaded(
        val planeLocal: PlaneLocal
    ): PlaneDetailsState()
}