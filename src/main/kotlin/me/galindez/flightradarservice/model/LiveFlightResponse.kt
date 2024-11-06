package me.galindez.flightradarservice.model

data class LiveFlightResponse(
    val flightId: String,
    val flightDate: String,
    val flightStatus: String,
    val updated: String,
    val latitude: Double,
    val longitude: Double,
    val altitude: Double,
    val direction: Double,
    val departure: String,
    val arrival: String,
    val estimatedArrival: String
)
