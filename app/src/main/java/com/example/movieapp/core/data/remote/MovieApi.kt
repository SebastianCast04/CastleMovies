package com.example.movieapp.core.data.remote

import com.example.movieapp.core.data.remote.dto.MovieDtoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    companion object {
        const val IMAGE_URL = "https://image.tmdb.org/t/p/original/"
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): MovieDtoResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieDtoResponse

    @GET("discover/movie?language=en-US&sort_by=popularity.desc")
    suspend fun getMoviesByGenreHorror(@Query("with_genres") with_genre:Int): MovieDtoResponse

    @GET("https://api.themoviedb.org/3/discover/movie?language=en-US&sort_by=popularity.desc&without_genres=28,35,80,99,18,27,53,10749,878,9648,10402,10751,10752,10770,12,14")
    suspend fun getMoviesByGenreAnimation(@Query("with_genres") with_genres:Int): MovieDtoResponse




}