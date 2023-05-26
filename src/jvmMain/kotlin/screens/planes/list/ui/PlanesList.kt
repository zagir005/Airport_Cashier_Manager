package screens.planes.list.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import data.plane.model.PlaneLocal
import screens.planes.list.PlaneScreenModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlanesList(
    planeList: List<PlaneLocal>,
    editPlane: (PlaneLocal) -> Unit = {},
    deletePlane: (PlaneLocal) -> Unit = {},
    planeClick: (PlaneLocal) -> Unit = {}
) {

    LazyVerticalStaggeredGrid(
        StaggeredGridCells.Adaptive(300.dp),
        contentPadding = PaddingValues(4.dp)
    ){
        itemsIndexed(planeList) {index, currentPlane ->
            PlaneCard(
                plane = currentPlane,
                seatCategories = PlaneScreenModel.getPlaneCategories(currentPlane),
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
