package com.example.movieapp.core.data

import com.example.movieapp.core.data.mapper.toDomain
import com.example.movieapp.core.data.remote.MovieApi
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.domain.repository.MovieRepository

class MovieRepositoryImpl(val api: MovieApi): MovieRepository {

    override suspend fun getUpcomingMovies(): Result<List<Movie>> {

        return try {

            val results = api.getUpcomingMovies().results
             Result.success(results.map { it.toDomain() })

        }catch (e: Exception){

            Result.failure(e)
        }

    }
}