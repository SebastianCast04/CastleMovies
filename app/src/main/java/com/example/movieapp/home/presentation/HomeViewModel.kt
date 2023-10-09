package com.example.movieapp.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.domain.model.FilterType
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
                val movies = launch { getAllMovies() }
                //val filtered = launch { getMoviesByFilter() }
                movies.join()
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
                        repository.getAllMovies(state.selectedFilter, true).collect{

                            state = state.copy(
                                filteredMovies = it.filtered
                            )


                        }
                        //getMoviesByFilter()
                    }
                }

            }
            is HomeEvent.onMovieClick -> TODO()
        }
    }

    private  suspend fun getAllMovies() {

        repository.getAllMovies(state.selectedFilter, false).collect{

            state = state.copy(
                upcoming = it.upcoming,
                popularMovies = it.trending,
                filteredMovies = it.filtered
            )
        }
    }

    /*private  suspend fun getUpcomingMovies() {

        repository.getUpcomingMovies().collect{
            state = state.copy(
                upcoming = it
            )
        }
    }

    private suspend fun  getPopularMovies() {

        repository.getPopularMovies().collect {
            state = state.copy(
                popularMovies = it
            )

        }
    }

     */

    /*private suspend fun getMoviesByFilter(){

        val result = when (state.selectedFilter){
            FilterType.HORROR -> repository.getMoviesByGenreHorror(27)
            FilterType.YEAR -> repository.getMoviesByGenreAnimation(16)
        }
        result.collect {
            if (it.isNotEmpty()) {
                state = state.copy(
                    filteredMovies = it.subList(0..6) //filter de recommended movies by just 6
                )
            }
        }
    }

     */
}