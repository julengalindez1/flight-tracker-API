package me.galindez.flightradarservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FlightRadarServiceApplication

fun main(args: Array<String>) {
	runApplication<FlightRadarServiceApplication>(*args)
}
