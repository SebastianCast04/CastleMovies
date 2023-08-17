package com.example.movieapp.core.data

import com.example.movieapp.core.data.mapper.toDomain
import com.example.movieapp.core.data.remote.MovieApi
import com.example.movieapp.core.data.remote.extensions.resultOf
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.domain.repository.MovieRepository

class MovieRepositoryImpl(private val api: MovieApi): MovieRepository {

    override suspend fun getUpcomingMovies() = resultOf {

        val results = api.getUpcomingMovies().results
        results.map { it.toDomain() }
    }

    override suspend fun getPopularMovies() = resultOf {

        val results = api.getPopularMovies().results
        results.map { it.toDomain() }
    }
}