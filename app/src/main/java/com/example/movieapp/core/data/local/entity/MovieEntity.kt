package com.example.movieapp.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val poster: String,
    val type: MovieType
)

enum class MovieType(){

    UPCOMING,
    TRENDING,
    HORROR,
    ANIMATED
}
