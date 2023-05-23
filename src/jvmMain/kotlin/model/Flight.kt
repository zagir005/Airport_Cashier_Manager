package model

import java.util.Date

data class Flight(
    val number: String,
    val plane: String,
    val company: String,
    val departureDate: Date,
    val arrivalDate: Date,
    val departureAirport: Airport,
    val arrivalAirport: Airport,
    val departureCity: String,
    val arrivalCity: String
)
