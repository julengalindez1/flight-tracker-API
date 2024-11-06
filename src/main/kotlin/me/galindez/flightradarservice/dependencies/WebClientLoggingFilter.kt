package me.galindez.flightradarservice.dependencies

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.ExchangeFunction
import reactor.core.publisher.Mono

class WebClientLoggingFilter: ExchangeFilterFunction {
    companion object
    {
        private val logger : Logger = LoggerFactory.getLogger(WebClientLoggingFilter::class.java)
    }

    override fun filter(request: ClientRequest, next: ExchangeFunction): Mono<ClientResponse> {
        logRequest(request)
        return next.exchange(request)
    }

    private fun logRequest(request: ClientRequest) {
        logger.debug("Request Method: {} \n Request URL: {} \n Request Header: {}",
            request.method(), request.url(), request.headers())
    }
}