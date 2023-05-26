package screens.planes.list.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import data.plane.model.PlaneLocal

@Composable
fun PlanesList(
    planeList: List<PlaneLocal>,
    seatCategoriesList: List<Map<String,Int>>,
    editPlane: (PlaneLocal) -> Unit = {},
    deletePlane: (PlaneLocal) -> Unit = {},
    planeClick: (PlaneLocal) -> Unit = {}
) {
    LazyVerticalGrid(
        GridCells.Adaptive(300.dp),
        contentPadding = PaddingValues(4.dp)
    ){
        itemsIndexed(planeList) {index, currentPlane ->
            PlaneCard(
                plane = currentPlane,
                seatCategories = seatCategoriesList[index],
                editPlane = {
                    editPlane(it)
                },
                deletePlane = {
                    deletePlane(it)
                },
                planeClick = {
                    planeClick(it)
                }
            )
        }
    }
}
