package me.galindez.flightradarservice.model.exception

enum class ApplicationErrorCode {
    API_ERROR_INVALID_LON,
    API_ERROR_INVALID_LAT,
    API_ERROR_NEGATIVE_RADIUS,
    API_ERROR_FLIGHTS_DATA_NOT_FOUND,
}