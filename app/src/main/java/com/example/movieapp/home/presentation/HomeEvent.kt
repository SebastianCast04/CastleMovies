package com.example.movieapp.home.presentation

import com.example.movieapp.core.domain.model.Movie

sealed class HomeEvent{

    data class ChangeFitler(val filterType: FilterType): HomeEvent()
    data class onMovieClick (val movieId:Int): HomeEvent()
}
