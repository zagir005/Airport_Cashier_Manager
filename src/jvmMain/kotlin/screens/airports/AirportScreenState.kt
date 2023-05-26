package screens.airports

import data.airport.model.AirportLocal

sealed class AirportScreenState{
    object Loading: AirportScreenState()

    data class AirportsListLoaded(val list: List<AirportLocal>): AirportScreenState()
}
