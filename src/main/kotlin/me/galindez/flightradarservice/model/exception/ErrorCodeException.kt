package me.galindez.flightradarservice.model.exception

import org.springframework.http.HttpStatus

class ErrorCodeException(
    val applicationErrorCode: ApplicationErrorCode,
    val httpStatus: HttpStatus,
    message: String ) : IllegalArgumentException(message) {
}