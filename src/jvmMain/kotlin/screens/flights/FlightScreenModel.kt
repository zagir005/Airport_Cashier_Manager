package screens.flights

import common.BaseStateScreenModel
import data.flight.datasource.FlightLocalCrudDataSource
import data.flight.model.FlightLocal
import org.mongodb.kbson.ObjectId

class FlightScreenModel(
    private val flightLocalCrudDataSource: FlightLocalCrudDataSource
): BaseStateScreenModel<FlightScreenState>(FlightScreenState.Loading) {

    init {
        loadFlights()
    }
    fun loadFlights(){
        launchIOCoroutine {
            mutableState.value = FlightScreenState.FlightListLoaded(
                flightLocalCrudDataSource.getAll(),
                flightLocalCrudDataSource.getAllPlanes(),
                flightLocalCrudDataSource.getAllAirports()
            )
        }
    }

    fun addFlight(flightLocal: FlightLocal){
        launchIOCoroutine {
            flightLocalCrudDataSource.add(flightLocal)
            loadFlights()
        }
    }

    fun flightSearch(query: String) {
        val flightState = (state.value as FlightScreenState.FlightListLoaded)
        if (query.isNotEmpty()) {
            updateState(
                FlightScreenState.FlightListLoaded(
                    flightState.flightList.filter {
                        it.flightNumber.contains(query, ignoreCase = true) ||
                        it.company.contains(query, ignoreCase = true)
                    },
                    flightState.planeList,
                    flightState.airportList
                )
            )
        } else {
            loadFlights()
        }

    }

    fun updateFlight(obj: FlightLocal){
        launchIOCoroutine {
            flightLocalCrudDataSource.update(obj)
            loadFlights()
        }
    }

    fun removeFlights(id: ObjectId){
        launchIOCoroutine {
            flightLocalCrudDataSource.removeById(id)
            loadFlights()
        }
    }
}