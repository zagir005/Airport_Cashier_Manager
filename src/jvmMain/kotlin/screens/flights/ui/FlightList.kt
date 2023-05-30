package screens.flights.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import data.flight.model.FlightLocal

@Composable
fun FlightsList(
    flightsList: List<FlightLocal>,
    editFlight: FlightLocalLambda = {},
    deleteFlight: FlightLocalLambda = {},
    onClickFlight: FlightLocalLambda = {},
    isAdminMode: Boolean
){
    LazyColumn{
        items(flightsList){
            FlightCard(
                it,
                editFlight,
                deleteFlight,
                isAdminMode,
                onClickFlight
            )
        }
    }
}
