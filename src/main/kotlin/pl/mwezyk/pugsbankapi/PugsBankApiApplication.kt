package pl.mwezyk.pugsbankapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PugsBankApiApplication

fun main(args: Array<String>) {
	runApplication<PugsBankApiApplication>(*args)
}
