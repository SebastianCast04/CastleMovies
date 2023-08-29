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

    override fun getUpcomingMovies(): Flow<List<Movie>> {

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

    override fun getPopularMovies(): Flow<List<Movie>> {

        return flow {
            val localMovies = dao.getMovies().filter { it.type == MovieType.TRENDING }
            emit(localMovies.map { it.toDomain() })
            getPopularMoviesRemotely().onSuccess {

                emit(it)

            }.onFailure {
                println()
            }
        }
    }

    private suspend fun getPopularMoviesRemotely() = resultOf {

        val results = api.getPopularMovies().results
        val movies = results.map { it.toDomain() }

        movies.forEach { dao.insertMovie(it.toEntity(MovieType.TRENDING)) }
        movies
    }

    override fun getMoviesByGenreHorror(with_genre: Int): Flow<List<Movie>> {

        return flow {
            val localMovies = dao.getMovies().filter { it.type == MovieType.TRENDING }
            emit(localMovies.map { it.toDomain() })
            getMoviesByGenreHorrorRemotely(with_genre).onSuccess {

                emit(it)

            }.onFailure {
                println()
            }
        }
    }

    private suspend fun getMoviesByGenreHorrorRemotely(with_genre:Int) = resultOf {

        val results = api.getMoviesByGenreHorror(with_genre).results
        val movies = results.map { it.toDomain() }

        movies.forEach { dao.insertMovie(it.toEntity(MovieType.HORROR)) }
        movies
    }

    override fun getMoviesByGenreAnimation(with_genres: Int): Flow<List<Movie>> {

        return flow {
            val localMovies = dao.getMovies().filter { it.type == MovieType.ANIMATED }
            emit(localMovies.map { it.toDomain() })
            getMoviesByGenreAnimationRemotely(with_genres).onSuccess {

                emit(it)

            }.onFailure {
                println()
            }
        }
    }

    private suspend fun getMoviesByGenreAnimationRemotely(with_genres:Int) = resultOf {

        val results = api.getMoviesByGenreAnimation(with_genres).results
        val movies = results.map { it.toDomain() }

        movies.forEach { dao.insertMovie(it.toEntity(MovieType.ANIMATED)) }
        movies
    }


}