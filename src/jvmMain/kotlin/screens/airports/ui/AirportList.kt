package screens.airports.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import data.airport.model.AirportLocal

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AirportsList(
    airportList: List<AirportLocal>,
    editAirport: (AirportLocal) -> Unit = {},
    deleteAirport: (AirportLocal) -> Unit = {}
){
    LazyVerticalStaggeredGrid(
        StaggeredGridCells.Adaptive(300.dp),
        contentPadding = PaddingValues(4.dp)
    ){
        items(airportList){
            AirportCard(
                airport = it,
                editAirport = editAirport,
                deleteAirport = deleteAirport
            )
        }
    }
}

