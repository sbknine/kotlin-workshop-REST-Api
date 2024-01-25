package com.example.kotlinworkshop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class KotlinWorkshopApplication
fun main(args: Array<String>) {
	runApplication<KotlinWorkshopApplication>(*args)
}
