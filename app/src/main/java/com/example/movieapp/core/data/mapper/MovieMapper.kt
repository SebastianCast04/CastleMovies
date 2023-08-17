package com.example.movieapp.core.data.mapper

import com.example.movieapp.core.data.remote.MovieApi
import com.example.movieapp.core.data.remote.dto.MovieResult
import com.example.movieapp.core.domain.model.Movie

fun MovieResult.toDomain(): Movie {

    return Movie(
        id = this.id,
        description = this.overview,
        title = this.title,
        releaseYear = this.release_date.substring(0,4).toInt(),
        language = this.original_language,
        rating = this.vote_average,
        poster = MovieApi.IMAGE_URL + this.poster_path,
        genres = this.genre_ids
    )

}