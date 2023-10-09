package com.example.movieapp.home.presentation

import com.example.movieapp.core.domain.model.FilterType
import com.example.movieapp.core.domain.model.Movie

data class HomeState(
    val upcoming: List<Movie> = emptyList(),
    val popularMovies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val selectedFilter: FilterType = FilterType.HORROR,
    val filteredMovies: List <Movie> = emptyList()

)