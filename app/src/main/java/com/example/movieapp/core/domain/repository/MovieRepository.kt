package com.example.movieapp.core.domain.repository

import com.example.movieapp.core.domain.model.Movie

interface MovieRepository {

    suspend fun getUpcomingMovies(): Result<List<Movie>>
    suspend fun getPopularMovies(): Result<List<Movie>>
}