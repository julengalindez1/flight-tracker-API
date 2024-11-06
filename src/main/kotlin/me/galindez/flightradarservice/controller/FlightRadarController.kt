package me.galindez.flightradarservice.controller

import me.galindez.flightradarservice.model.exception.ErrorCodeException
import me.galindez.flightradarservice.service.FlightRadarService
import me.galindez.flightradarservice.utils.FlightRadarControllerUtils.validateGetNearbyInput
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/flights")
class FlightRadarController(private val flightRadarService: FlightRadarService) {

    companion object
    {
        private val logger : Logger = LoggerFactory.getLogger(FlightRadarController::class.java)
    }

    @GetMapping("/all")
    fun getNearbyFlights(): ResponseEntity<Any> {
        try {

            return flightRadarService.getAllFlights()

        } catch (ex: ErrorCodeException) {

            logger.error("Error occurred: {}", ex.message)
            return ResponseEntity
                .status(ex.httpStatus)
                .body(mapOf("errorCode" to ex.applicationErrorCode.name, "message" to ex.message))

        } catch (ex: Exception) {

            logger.error("Unexpected error: {}", ex.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(mapOf("errorCode" to "UNKNOWN_ERROR", "message" to "An unexpected error occurred."))
        }
    }

    @GetMapping("/nearby")
    fun getNearbyFlights(
        @RequestParam("latitude") latitude: Double,
        @RequestParam("longitude") longitude: Double,
        @RequestParam("radius") radius: Double
    ): ResponseEntity<Any> {
        try {
            validateGetNearbyInput(latitude, longitude, radius)
            return flightRadarService.getNearbyFlights(latitude, longitude, radius)

        } catch (ex: ErrorCodeException) {

            logger.error("Error occurred: {}", ex.message)
            return ResponseEntity
                .status(ex.httpStatus)
                .body(mapOf("errorCode" to ex.applicationErrorCode.name, "message" to ex.message))

        } catch (ex: Exception) {

            logger.error("Unexpected error: {}", ex.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(mapOf("errorCode" to "UNKNOWN_ERROR", "message" to "An unexpected error occurred."))
        }
    }

    @GetMapping("/live")
    fun getLiveFlights(): ResponseEntity<Any> {
        try {
            return flightRadarService.getLiveFlights()

        } catch (ex: ErrorCodeException) {

            logger.error("Error occurred: {}", ex.message)
            return ResponseEntity
                .status(ex.httpStatus)
                .body(mapOf("errorCode" to ex.applicationErrorCode.name, "message" to ex.message))

        } catch (ex: Exception) {

            logger.error("Unexpected error: {}", ex.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(mapOf("errorCode" to "UNKNOWN_ERROR", "message" to "An unexpected error occurred."))
        }
    }
}