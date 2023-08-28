package com.example.movieapp.core.data

import com.example.movieapp.core.data.local.MovieDao
import com.example.movieapp.core.data.local.entity.MovieType
import com.example.movieapp.core.data.mapper.toDomain
import com.example.movieapp.core.data.mapper.toEntity
import com.example.movieapp.core.data.remote.MovieApi
import com.example.movieapp.core.data.remote.extensions.resultOf
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(private val api: MovieApi, private val dao: MovieDao) : MovieRepository {

    override fun getUpcomingMovies():Flow<List<Movie>> {

        //Getting the all the movies from the database
        return flow {
            val localMovies = dao.getMovies().filter { it.type == MovieType.UPCOMING }
            emit(localMovies.map { it.toDomain() })
            getUpComingMoviesRemotely().onSuccess {

                emit(it)

            }.onFailure {
                println()
            }


        }
    }
    private suspend fun getUpComingMoviesRemotely() = resultOf {

        val results = api.getUpcomingMovies().results
        val movies = results.map { it.toDomain() }
        movies.forEach { dao.insertMovie(it.toEntity(MovieType.UPCOMING)) }
        movies
    }

    override suspend fun getPopularMovies() = resultOf {

        val results = api.getPopularMovies().results
        results.map { it.toDomain() }
    }

    override suspend fun getMoviesByGenreHorror(with_genre: Int) = resultOf {

        val results = api.getMoviesByGenreHorror(with_genre).results
        results.map { it.toDomain() }
    }

    override suspend fun getMoviesByGenreAnimation(with_genres: Int) = resultOf {

        val results = api.getMoviesByGenreAnimation(with_genres).results
        results.map { it.toDomain() }
    }


}