package dev.nemuki.cypherbookapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CypherBookApiApplication

fun main(args: Array<String>) {
    runApplication<CypherBookApiApplication>(*args)
}
