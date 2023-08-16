package com.example.movieapp.home.presentation

import com.example.movieapp.core.domain.model.Movie

data class HomeState(
    val upcoming: List<Movie> = emptyList(),
    val isLoading: Boolean = false

)