package me.galindez.flightradarservice.model.aviationstackapi

data class FlightDataResponse(
    val data: List<FlightData>
)

data class FlightData(
    val flight_date: String,
    val flight_status: String,
    val departure: Departure,
    val arrival: Arrival,
    val airline: Airline,
    val flight: Flight,
    val aircraft: Aircraft?,
    val live: Live?
)

data class Departure(
    val airport: String,
    val timezone: String?,
    val iata: String?,
    val icao: String?,
    val terminal: String?,
    val gate: String?,
    val delay: Int?,
    val scheduled: String?,
    val estimated: String?,
    val actual: String?,
    val estimated_runway: String?,
    val actual_runway: String?
)

data class Arrival(
    val airport: String,
    val timezone: String?,
    val iata: String?,
    val icao: String?,
    val terminal: String?,
    val gate: String?,
    val baggage: String?,
    val delay: Int?,
    val scheduled: String?,
    val estimated: String?,
    val actual: String?,
    val estimated_runway: String?,
    val actual_runway: String?
)

data class Airline(
    val name: String,
    val iata: String?,
    val icao: String?
)

data class Flight(
    val number: String,
    val iata: String?,
    val icao: String?,
    val codeshared: Codeshared?
)

data class Codeshared(
    val airline_name: String?,
    val airline_iata: String?,
    val airline_icao: String?,
    val flight_number: String?,
    val flight_iata: String?,
    val flight_icao: String?
)

data class Aircraft(
    val registration: String?,
    val iata: String?,
    val icao: String?,
    val icao24: String?
)

data class Live(
    val updated: String?,
    val latitude: Double?,
    val longitude: Double?,
    val altitude: Double?,
    val direction: Double?,
    val speed_horizontal: Double?,
    val speed_vertical: Double?,
    val is_ground: Boolean?
)

