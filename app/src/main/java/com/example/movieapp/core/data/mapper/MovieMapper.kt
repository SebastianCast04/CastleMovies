package com.example.movieapp.core.data.mapper

import com.example.movieapp.core.data.local.entity.MovieEntity
import com.example.movieapp.core.data.local.entity.MovieType
import com.example.movieapp.core.data.remote.MovieApi
import com.example.movieapp.core.data.remote.dto.MovieResult
import com.example.movieapp.core.domain.model.Movie

fun MovieResult.toDomain(): Movie {

    return Movie(
        id = this.id,
        poster = MovieApi.IMAGE_URL + this.poster_path,
    )

}

fun Movie.toEntity(type: MovieType): MovieEntity {

    return MovieEntity(
        id = this.id,
        poster = this.poster,
        type = type

    )

}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = this.id,
        poster = this.poster

    )

}