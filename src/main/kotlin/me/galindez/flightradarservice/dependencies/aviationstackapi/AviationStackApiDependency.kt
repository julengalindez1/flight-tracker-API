package me.galindez.flightradarservice.dependencies.aviationstackapi

import com.google.gson.Gson
import me.galindez.flightradarservice.model.aviationstackapi.FlightData
import me.galindez.flightradarservice.model.aviationstackapi.FlightDataResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono
import reactor.util.retry.Retry
import java.time.Duration

@Service
class AviationStackApiDependency(
    private val webClient: WebClient,
    @Value("\${app.aviationStack.api.key}") private val apiKey: String,
    @Value("\${app.aviationStackApi.numberOfRetries}") private val numberOfRetries: Long,
    @Value("\${app.aviationStackApi.retryBackoffDuration}") private val retryBackoffDuration: Duration
) {
    companion object
    {
        private val logger : Logger = LoggerFactory.getLogger(AviationStackApiDependency::class.java)
    }

    fun getFlightsData(): List<FlightData>? {
        return getDataMonoByUri(AviationStackApiUris.GET_FLIGHTS.uri)
            .map { response -> Gson().fromJson(response, FlightDataResponse::class.java).data}
            .block()
    }

    private fun getDataMonoByUri(uri: String): Mono<String> {
        val completeUri = webClient.get()
            .uri { uriBuilder ->
                uriBuilder.path(uri)
                    .queryParam("access_key", apiKey)
                    .build()
            }

        logger.info("Request URI: $completeUri")

        return completeUri
            .retrieve()
            .bodyToMono(String::class.java)
            .onErrorMap { error ->
                handleClientError(error, uri)
            }
            .retryWhen(
                Retry.backoff(numberOfRetries, retryBackoffDuration)
                    .filter { throwable ->
                        throwable is WebClientResponseException && throwable.statusCode.is5xxServerError
                    }
                    .onRetryExhaustedThrow { _, signal ->
                        Exception("Max retries reached for $uri after ${signal.totalRetries()} attempts", signal.failure())
                    }
            )
    }

    private fun handleClientError(error: Throwable, request: String): Throwable {
        return when (error) {
            is WebClientResponseException -> {
                logger.error("WebClientResponseException {}: {}", request, error.responseBodyAsString)
                Exception(error.responseBodyAsString)
            }
            else -> {
                logger.error("Unexpected error in {} : {}", request, error.message)
                Exception("Internal Server Error: AviationStack API request failed")
            }
        }
    }
}