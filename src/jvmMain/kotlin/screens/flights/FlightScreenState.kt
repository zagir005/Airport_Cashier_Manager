package screens.flights

import data.airport.model.AirportLocal
import data.flight.model.FlightLocal
import data.plane.model.PlaneLocal

sealed class FlightScreenState {
    object Loading: FlightScreenState()

    data class FlightListLoaded(
        val flightList: List<FlightLocal>,
        val planeList: List<PlaneLocal>,
        val airportList: List<AirportLocal>
    ): FlightScreenState()
}