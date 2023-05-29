package data.flight.datasource

import common.LocalCrudDataSource
import data.airport.model.AirportLocal
import data.flight.model.FlightLocal
import data.plane.model.PlaneLocal

interface FlightLocalCrudDataSource: LocalCrudDataSource<FlightLocal>{
    suspend fun getAllPlanes(): List<PlaneLocal>

    suspend fun getAllAirports(): List<AirportLocal>
}