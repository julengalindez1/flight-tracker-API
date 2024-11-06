package me.galindez.flightradarservice
import me.galindez.flightradarservice.model.aviationstackapi.*

class TestUtils {
    companion object {
        fun readJsonFile(fileName: String): String {
            val resource = this.javaClass.classLoader.getResource(fileName)
            return resource?.readText() ?: throw IllegalArgumentException("File not found: $fileName")
        }

        fun createFlightDataForMockTest(): List<FlightData> {
            return listOf(
                FlightData(
                    flight_date = "2024-11-05",
                    flight_status = "scheduled",
                    departure = Departure(
                        airport = "Da Nang",
                        timezone = "Asia/Ho_Chi_Minh",
                        iata = "DAD",
                        icao = "VVDN",
                        terminal = "2",
                        gate = null,
                        delay = 69,
                        scheduled = "2024-11-05T02:15:00+00:00",
                        estimated = "2024-11-05T02:15:00+00:00",
                        actual = "2024-11-05T03:23:00+00:00",
                        estimated_runway = "2024-11-05T03:23:00+00:00",
                        actual_runway = "2024-11-05T03:23:00+00:00"
                    ),
                    arrival = Arrival(
                        airport = "Cheongju",
                        timezone = "Asia/Seoul",
                        iata = "CJJ",
                        icao = "RKTU",
                        terminal = "1",
                        gate = null,
                        baggage = null,
                        delay = 46,
                        scheduled = "2024-11-05T08:35:00+00:00",
                        estimated = "2024-11-05T08:35:00+00:00",
                        actual = null,
                        estimated_runway = null,
                        actual_runway = null
                    ),
                    airline = Airline(
                        name = "Aero K",
                        iata = "RF",
                        icao = "EOK"
                    ),
                    flight = Flight(
                        number = "532",
                        iata = "RF532",
                        icao = "EOK532",
                        codeshared = null
                    ),
                    aircraft = Aircraft(
                        registration = "HL8386",
                        iata = "A320",
                        icao = "A320",
                        icao24 = "71C386"
                    ),
                    live = Live(
                        updated = "2024-11-04T22:40:00+00:00",
                        latitude = 26.0701,
                        longitude = 122.431,
                        altitude = 10058.4,
                        direction = 35.35,
                        speed_horizontal = 921.888,
                        speed_vertical = 0.0,
                        is_ground = false
                    )
                ),
                FlightData(
                    flight_date = "2024-11-05",
                    flight_status = "active",
                    departure = Departure(
                        airport = "Singapore Changi",
                        timezone = "Asia/Singapore",
                        iata = "SIN",
                        icao = "WSSS",
                        terminal = "1",
                        gate = "D44",
                        delay = 41,
                        scheduled = "2024-11-05T00:25:00+00:00",
                        estimated = "2024-11-05T00:25:00+00:00",
                        actual = "2024-11-05T01:05:00+00:00",
                        estimated_runway = "2024-11-05T01:05:00+00:00",
                        actual_runway = "2024-11-05T01:05:00+00:00"
                    ),
                    arrival = Arrival(
                        airport = "Jeju Airport",
                        timezone = "Asia/Seoul",
                        iata = "CJU",
                        icao = "RKPC",
                        terminal = "D",
                        gate = "4",
                        baggage = null,
                        delay = 2,
                        scheduled = "2024-11-05T07:25:00+00:00",
                        estimated = "2024-11-05T07:25:00+00:00",
                        actual = null,
                        estimated_runway = null,
                        actual_runway = null
                    ),
                    airline = Airline(
                        name = "Scoot",
                        iata = "TR",
                        icao = "TGW"
                    ),
                    flight = Flight(
                        number = "812",
                        iata = "TR812",
                        icao = "TGW812",
                        codeshared = null
                    ),
                    aircraft = Aircraft(
                        registration = "9V-NCH",
                        iata = "A21N",
                        icao = "A21N",
                        icao24 = "76B868"
                    ),
                    live = Live(
                        updated = "2024-11-04T21:14:53+00:00",
                        latitude = 25.8256,
                        longitude = 122.241,
                        altitude = 10668.0,
                        direction = 35.46,
                        speed_horizontal = 932.22,
                        speed_vertical = 0.0,
                        is_ground = false
                    )
                )
            )
        }
    }
}