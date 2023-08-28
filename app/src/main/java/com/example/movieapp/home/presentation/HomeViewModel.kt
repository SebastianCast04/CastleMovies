package com.example.movieapp.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MovieRepository): ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            supervisorScope {

                //This is for running to courutines at the same time, but in just one
                val upcoming = launch { getUpcomingMovies() }
                val popular = launch { getPopularMovies() }
                val filtered = launch { getMoviesByFilter() }

                listOf(upcoming, popular, filtered).forEach { it.join() }
                state = state.copy(isLoading = false)

            }
        }
    }

    fun onEvent(event:HomeEvent){

        when(event){
            is HomeEvent.ChangeFitler -> {
                if (event.filterType != state.selectedFilter){

                    state = state.copy(
                        selectedFilter = event.filterType
                    )
                    // Brings the movies when the filter is changed
                    viewModelScope.launch {
                        getMoviesByFilter()
                    }
                }

            }
            is HomeEvent.onMovieClick -> TODO()
        }
    }

    private  suspend fun getUpcomingMovies() {

        repository.getUpcomingMovies().collect{
            state = state.copy(
                upcoming = it
            )
        }
    }

    private suspend fun  getPopularMovies() {

        repository.getPopularMovies().onSuccess {
            state = state.copy(
                popularMovies = it
            )

        }.onFailure {
            println()
        }
    }

    private suspend fun getMoviesByFilter(){

        val result = when (state.selectedFilter){
            FilterType.HORROR -> repository.getMoviesByGenreHorror(27)
            FilterType.YEAR -> repository.getMoviesByGenreAnimation(16)
        }

        result.onSuccess {

            state = state.copy(
                filteredMovies = it //it.subList(0..6) filter de recommended movies by just 6
            )

        }.onFailure {
            println()
        }


    }
}