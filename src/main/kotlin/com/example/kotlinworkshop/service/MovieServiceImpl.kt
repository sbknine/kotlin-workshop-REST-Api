package com.example.kotlinworkshop.service

import com.example.kotlinworkshop.dto.MovieDTO
import com.example.kotlinworkshop.entity.Movie
import com.example.kotlinworkshop.repository.MovieRepository
import com.example.kotlinworkshop.utils.exceptions.MovieException
import com.example.kotlinworkshop.utils.mapper.MovieMapper
import org.springframework.stereotype.Service

@Service
class MovieServiceImpl(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) : MovieService {
    override fun createMovie(movieDTO: MovieDTO):MovieDTO {

        if(movieDTO.id != -1) {
            throw MovieException("Id must be null or -1")
        }
        val movie = movieRepository.save(movieMapper.toEntity(movieDTO))
        return movieMapper.fromEntity(movie)
    }

    override fun getMovies(): List<MovieDTO> {
//        val movieIterable = movieRepository.findAll()
//        val movies = mutableListOf<MovieDTO>()
//        movieIterable.forEach{
//            movies.add(movieMapper.fromEntity(it))
//        }

        val movies = movieRepository.getALlMovies()

        if(movies.isEmpty()){
            throw MovieException("List of movies is empty")
        }

        return  movies.map {
            movieMapper.fromEntity(it)
        }
    }

    override fun getMovie(id: Int): MovieDTO {
        val optionalMovie = movieRepository.findById(id)
        val movie = optionalMovie.orElseThrow{ MovieException("Movie with id $id is no present") }
        return  movieMapper.fromEntity(movie)
    }

    override fun updateMovie(movieDTO: MovieDTO): MovieDTO {
        val exists = movieRepository.existsById(movieDTO.id)
        if(!exists){
            throw MovieException("Movie with id ${movieDTO.id} is no present")
        }
        if(movieDTO.rating == 0.0 || movieDTO.name == "Default movie") {
            throw MovieException("Complete movie object is expected")
        }
        movieRepository.save(movieMapper.toEntity(movieDTO))
        return movieDTO
    }

    override fun deleteMovie(id: Int) {
        val exists = movieRepository.existsById(id)
        if(!exists){
            throw MovieException("Movie with id $id is no present")
        }

        movieRepository.deleteById(id)
    }
}