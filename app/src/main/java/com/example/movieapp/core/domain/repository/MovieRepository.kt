package com.example.movieapp.core.domain.repository

import com.example.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getUpcomingMovies(): Flow<List<Movie>>
    suspend fun getPopularMovies(): Result<List<Movie>>
    suspend fun getMoviesByGenreHorror(with_genre: Int): Result<List<Movie>>

    suspend fun getMoviesByGenreAnimation(with_genres: Int): Result<List<Movie>>
}