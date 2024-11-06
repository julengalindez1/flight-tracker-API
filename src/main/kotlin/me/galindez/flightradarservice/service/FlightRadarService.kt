package me.galindez.flightradarservice.service

import me.galindez.flightradarservice.dependencies.aviationstackapi.AviationStackApiDependency
import me.galindez.flightradarservice.model.LiveFlightResponse
import me.galindez.flightradarservice.model.aviationstackapi.FlightData
import me.galindez.flightradarservice.model.exception.ApplicationErrorCode
import me.galindez.flightradarservice.model.exception.ErrorCodeException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class FlightRadarService(
    private val aviationStackApiDependency: AviationStackApiDependency,
    private val dataProcessingService: DataProcessingService) {
    companion object
    {
        private val logger : Logger = LoggerFactory.getLogger(FlightRadarService::class.java)
    }

    fun getAllFlights(): ResponseEntity<Any>
    {
        logger.debug("Fetching all flights...")

        val flightData: List<FlightData> = aviationStackApiDependency.getFlightsData()
            ?: throw ErrorCodeException(ApplicationErrorCode.API_ERROR_FLIGHTS_DATA_NOT_FOUND, HttpStatus.NOT_FOUND, " Something went wrong when fetching flights data from Aviation Stack API ")

        return ResponseEntity.ok(flightData)
    }

    fun getNearbyFlights(
        latitude: Double,
        longitude: Double,
        radius: Double
    ): ResponseEntity<Any>
    {
        logger.debug("Finding flights close the following coordinates: latitude: {}, longitude: {}, and in a radius of: {}", latitude, longitude, radius)

        val flightData: List<FlightData> = aviationStackApiDependency.getFlightsData()
            ?: throw ErrorCodeException(ApplicationErrorCode.API_ERROR_FLIGHTS_DATA_NOT_FOUND, HttpStatus.NOT_FOUND, " Something went wrong when fetching flights data from Aviation Stack API ")

        val foundLiveFlightsByCoordinate =
            dataProcessingService.findLiveFlightsByCoordinate(flightData, latitude, longitude, radius)
        return ResponseEntity.ok(foundLiveFlightsByCoordinate)
    }

    fun getLiveFlights(): ResponseEntity<Any> {
        logger.debug("Fetching live flights data")

        val flightData: List<FlightData> = aviationStackApiDependency.getFlightsData()
            ?: throw ErrorCodeException(ApplicationErrorCode.API_ERROR_FLIGHTS_DATA_NOT_FOUND,
                HttpStatus.NOT_FOUND, "No flights data found.")

        val liveFlights = flightData.filter { it.live != null && !(it.live.is_ground ?: true) }
            .map { flight ->
                LiveFlightResponse(
                    flightId = flight.flight.iata ?: "Unknown Flight ID",
                    flightDate = flight.flight_date,
                    flightStatus = flight.flight_status,
                    updated = flight.live?.updated ?: "Unknown time",
                    latitude = flight.live?.latitude ?: 0.0,
                    longitude = flight.live?.longitude ?: 0.0,
                    altitude = flight.live?.altitude ?: 0.0,
                    direction = flight.live?.direction ?: 0.0,
                    departure = flight.departure.airport,
                    arrival = flight.arrival.airport,
                    estimatedArrival = flight.arrival.estimated ?: "Unknown Estimated Arrival"
                )
            }

        return ResponseEntity.ok(liveFlights)
    }
}