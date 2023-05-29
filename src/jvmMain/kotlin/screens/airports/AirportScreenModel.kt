package screens.airports

import common.BaseStateScreenModel
import data.airport.datasource.AirportLocalCrudDataSource
import data.airport.model.AirportLocal

class AirportScreenModel(
    private val airportLocalCrudDataSource: AirportLocalCrudDataSource
): BaseStateScreenModel<AirportScreenState>(AirportScreenState.Loading) {
    init {
        loadAirports()
    }

    fun loadAirports(){
        launchIOCoroutine {
            updateState(
                AirportScreenState.AirportsListLoaded(
                    airportLocalCrudDataSource.getAll()
                )
            )
        }
    }

    fun airportSearch(query: String) {
        if (query.isNotEmpty()) {
            val list = (state.value as AirportScreenState.AirportsListLoaded).list
            updateState(
                AirportScreenState.AirportsListLoaded(
                    list.filter {
                        it.name.contains(query, ignoreCase = true) ||
                        it.city.contains(query, ignoreCase = true) ||
                        it.cityCode.contains(query, ignoreCase = true)
                    }
                )
            )
        } else {
            loadAirports()
        }
    }

    fun addAirport(airportLocal: AirportLocal){
        launchIOCoroutine {
            airportLocalCrudDataSource.add(airportLocal)
            loadAirports()
        }
    }

    fun updateAirport(airportLocal: AirportLocal){
        launchIOCoroutine {
            airportLocalCrudDataSource.update(airportLocal)
            loadAirports()
        }
    }

    fun deleteAirport(airportLocal: AirportLocal){
        launchIOCoroutine {

            airportLocalCrudDataSource.remove(airportLocal)
            loadAirports()
        }
    }
}