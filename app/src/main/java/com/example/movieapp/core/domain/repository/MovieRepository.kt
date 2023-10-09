package com.example.movieapp.core.domain.repository

import com.example.movieapp.core.domain.model.FilterType
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.domain.model.MovieList
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    /* fun getUpcomingMovies(): Flow<List<Movie>>
     fun getPopularMovies(): Flow<List<Movie>>
     fun getMoviesByGenreHorror(with_genre: Int): Flow<List<Movie>>

     fun getMoviesByGenreAnimation(with_genres: Int): Flow<List<Movie>>

     */



     fun getAllMovies(filterType: FilterType,  isFilteredOnly:Boolean): Flow<MovieList>
}