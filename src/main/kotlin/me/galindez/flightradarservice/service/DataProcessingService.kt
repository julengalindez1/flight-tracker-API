package me.galindez.flightradarservice.service

import me.galindez.flightradarservice.model.aviationstackapi.FlightData
import me.galindez.flightradarservice.utils.FlightRadarServiceUtils.calculateHaversineDistance
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import kotlin.math.*

@Service
class DataProcessingService {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(DataProcessingService::class.java)
    }

    fun findLiveFlightsByCoordinate(
        flightData: List<FlightData>,
        latitude: Double,
        longitude: Double,
        radius: Double
    ): List<FlightData> {

        return flightData.filter { flight ->
            val live = flight.live
            if (live?.latitude != null && live.longitude != null) {
                val distance = calculateHaversineDistance(
                    latitude, longitude, live.latitude, live.longitude
                )
                distance <= radius
            } else {
                false
            }
        }
    }

    /* Apply Haversine formula to compute the distance in kilometers between two coordinates*/
    private fun calculateDistance(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Double {
        val earthRadiusKm = 6371.0

        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)

        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2) * sin(dLon / 2)

        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return earthRadiusKm * c
    }
}