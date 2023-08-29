package com.example.movieapp.core.domain.model

data class MovieList(
    val movieMap: Map<MovieType, List<Movie>>
)
