package me.galindez.flightradarservice.utils

import me.galindez.flightradarservice.model.exception.ApplicationErrorCode
import me.galindez.flightradarservice.model.exception.ErrorCodeException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

object FlightRadarControllerUtils {

    fun validateGetNearbyInput(userLat: Double, userLon: Double, userRadius: Double) : ResponseEntity<Any>? {

        if (userRadius < 0) {
            throw ErrorCodeException(ApplicationErrorCode.API_ERROR_NEGATIVE_RADIUS, HttpStatus.BAD_REQUEST, "Invalid radius value. It cannot be negative.")
        }
        if (userLat !in -90.0..90.0 ) {
            throw ErrorCodeException(ApplicationErrorCode.API_ERROR_INVALID_LAT, HttpStatus.BAD_REQUEST, "Invalid latitude value. It must be between -90 and 90, provided latitude: $userLat")
        }
        if (userLon !in -180.0..180.0) {
            throw ErrorCodeException(ApplicationErrorCode.API_ERROR_INVALID_LON, HttpStatus.BAD_REQUEST, "Invalid longitude value. It must be between -180 and 180, provided longitude: $userLon")
        }
        return null
    }
}