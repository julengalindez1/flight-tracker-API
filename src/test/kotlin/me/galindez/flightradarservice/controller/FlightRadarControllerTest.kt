package me.galindez.flightradarservice.controller

import me.galindez.flightradarservice.TestUtils
import me.galindez.flightradarservice.dependencies.aviationstackapi.AviationStackApiDependency
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(MockitoExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class FlightRadarControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var aviationStackApiDependency: AviationStackApiDependency

    companion object {
        private const val FLIGHTS_NEARBY_EXAMPLE_URI = "/api/v1/flights/nearby?latitude=26.44&longitude=122.431&radius=100"
    }

    @BeforeEach
    fun setUp() {
        `when`(aviationStackApiDependency.getFlightsData()).thenReturn(TestUtils.createFlightDataForMockTest())
    }

    @Test
    @DisplayName("should return 200 response")
    fun getDeliveryOrderPriceTest_OK() {
        webTestClient.get()
            .uri(FLIGHTS_NEARBY_EXAMPLE_URI)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
    }
}