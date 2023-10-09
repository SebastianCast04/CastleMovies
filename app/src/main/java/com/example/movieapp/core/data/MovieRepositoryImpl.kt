package com.example.movieapp.core.data

import com.example.movieapp.core.data.local.MovieDao
import com.example.movieapp.core.data.local.entity.MovieEntity
import com.example.movieapp.core.data.local.entity.MovieType
import com.example.movieapp.core.data.mapper.toDomain
import com.example.movieapp.core.data.mapper.toEntity
import com.example.movieapp.core.data.remote.MovieApi
import com.example.movieapp.core.data.remote.extensions.resultOf
import com.example.movieapp.core.domain.model.FilterType
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.domain.model.MovieList
import com.example.movieapp.core.domain.repository.MovieRepository
import com.example.movieapp.core.domain.usecase.FilteringMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class MovieRepositoryImpl(
    private val api: MovieApi,
    private val dao: MovieDao,
    private val FilteringMovies:FilteringMovies
) : MovieRepository {


    private suspend fun getUpComingMoviesRemotely() = resultOf {

        val results = api.getUpcomingMovies().results
        val movies = results.map { it.toDomain() }
        movies
    }


    private suspend fun getPopularMoviesRemotely() = resultOf {

        val results = api.getPopularMovies().results
        val movies = results.map { it.toDomain() }
        movies
    }

    private suspend fun getMoviesByGenreHorrorRemotely(with_genre: Int) = resultOf {

        val results = api.getMoviesByGenreHorror(with_genre).results
        val movies = results.map { it.toDomain() }
        movies
    }

    private suspend fun getMoviesByGenreAnimationRemotely(with_genres: Int) = resultOf {

        val results = api.getMoviesByGenreAnimation(with_genres).results
        val movies = results.map { it.toDomain() }
        movies
    }


    /*
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
    */

    /*

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
   */


    override fun getAllMovies(filterType: FilterType, isFilteredOnly: Boolean): Flow<MovieList> {

        return flow {

            emit(getMoviesLocally(filterType))

            if (!isFilteredOnly) {

                getUpComingMoviesRemotely().onSuccess {

                    saveMovieLocally(it, MovieType.UPCOMING)
                    emit(getMoviesLocally(filterType))

                }.onFailure {
                    println()
                }

                getPopularMoviesRemotely().onSuccess {

                    saveMovieLocally(it, MovieType.TRENDING)
                    emit(getMoviesLocally(filterType))

                }.onFailure {
                    println()
                }

                getMoviesByGenreHorrorRemotely(27).onSuccess {

                    saveMovieLocally(it, MovieType.TRENDING)
                    emit(getMoviesLocally(filterType))

                }.onFailure {
                    println()
                }

                getMoviesByGenreHorrorRemotely(27).onSuccess {

                    saveMovieLocally(it, MovieType.HORROR)
                    emit(getMoviesLocally(filterType))

                }.onFailure {
                    println()
                }

                getMoviesByGenreAnimationRemotely(16).onSuccess {

                    saveMovieLocally(it, MovieType.ANIMATED)
                    emit(getMoviesLocally(filterType))

                }.onFailure {
                    println()
                }

            }

            //TODO: Call filtered

        }
    }


    private suspend fun saveMovieLocally(movies: List<Movie>, movieType: MovieType) {

        movies.forEach { dao.insertMovie(it.toEntity(movieType)) }

    }

    private suspend fun getMoviesLocally(filterType: FilterType): MovieList {

        val localMovies = dao.getMovies()

        val movieTypeFromFilter = when (filterType) {

            FilterType.HORROR -> MovieType.HORROR
            FilterType.YEAR -> MovieType.ANIMATED
        }


        return MovieList(
            upcoming = filterAndMapMovies(localMovies, MovieType.UPCOMING),
            trending = filterAndMapMovies(localMovies, MovieType.TRENDING),
            filtered = FilteringMovies(filterAndMapMovies(localMovies, movieTypeFromFilter))


        )
    }

    private fun filterAndMapMovies(movie: List<MovieEntity>, movieType: MovieType): List<Movie> {

        return movie.filter { it.type == movieType }.map { it.toDomain() }


    }


}