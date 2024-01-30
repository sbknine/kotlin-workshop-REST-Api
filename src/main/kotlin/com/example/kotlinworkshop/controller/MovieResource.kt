package com.example.kotlinworkshop.controller

import com.example.kotlinworkshop.dto.MovieDTO
import com.example.kotlinworkshop.service.MovieService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MovieResource ( private val movieService: MovieService) {

    @PostMapping
    fun createMovie(@RequestBody movieDTO: MovieDTO): ResponseEntity<MovieDTO> {
        return ResponseEntity(movieService.createMovie(movieDTO), HttpStatus.CREATED)
    }

    @GetMapping
    fun getMovies(): ResponseEntity<List<MovieDTO>> {
        return ResponseEntity.ok(movieService.getMovies())
    }

    @GetMapping("/{id}")
    fun getMovie(@PathVariable id: Int) = ResponseEntity.ok(movieService.getMovie(id))

    @PutMapping
    fun updateMovie(@RequestBody movieDTO: MovieDTO): ResponseEntity<MovieDTO> =
        ResponseEntity.ok(movieService.updateMovie(movieDTO))

    @DeleteMapping("/{id}")
    fun deleteMovie(@PathVariable id: Int): ResponseEntity<Unit> =
        ResponseEntity(movieService.deleteMovie(id), HttpStatus.NO_CONTENT)
}