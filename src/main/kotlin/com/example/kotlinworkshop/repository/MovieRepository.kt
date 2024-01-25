package com.example.kotlinworkshop.repository

import com.example.kotlinworkshop.dto.MovieDTO
import com.example.kotlinworkshop.entity.Movie
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface MovieRepository: CrudRepository<Movie, Int> {

    @Query("SELECT m FROM Movie as m")
    fun getALlMovies(): List<Movie>
}