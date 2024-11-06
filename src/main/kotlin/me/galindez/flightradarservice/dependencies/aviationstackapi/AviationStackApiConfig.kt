package me.galindez.flightradarservice.dependencies.aviationstackapi

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class AviationStackApiConfig {

    @Value("\${app.aviationStack.api.host}")
    lateinit var aviationStackApiHost: String

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .baseUrl(aviationStackApiHost)
            .build()
    }
}