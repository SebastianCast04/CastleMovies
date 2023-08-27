package com.example.movieapp.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.R
import com.example.movieapp.home.presentation.components.HomeHeader
import com.example.movieapp.home.presentation.components.HomeMovieList
import com.example.movieapp.home.presentation.components.HomeMoviePoster
import com.example.movieapp.home.presentation.components.MoviePosterSize
import com.example.movieapp.home.presentation.components.RecommendedForYou
import com.example.movieapp.home.presentation.components.Titles

const val COLUMNS_IN_GRID = 2

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val state = viewModel.state
    /*LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 25.dp)
    ) {

        item {
            HomeHeader()
        }

        if (state.upcoming.isNotEmpty()) {
            item {

                HomeMovieList("Upcoming Movies", posters = state.upcoming.map { it.poster })
            }
        }

        item {
            Spacer(modifier = Modifier.height(26.dp))
        }

        if (state.popularMovies.isNotEmpty()) {
            item {

                HomeMovieList("Popular", posters = state.popularMovies.map { it.poster })
            }
        }
        
        item { 
            Spacer(modifier = Modifier.height(16.dp))
        }

        item { 
            
            RecommendedForYou(selectedFilterType = state.selectedFilter, onFilterClick = {
                viewModel.onEvent(HomeEvent.ChangeFitler(it))
            }, movieList = state.filteredMovies){

            }
        }
    }
     */

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)

    ) {

        item (span = {
            GridItemSpan(COLUMNS_IN_GRID)
        }){
            HomeHeader()
        }

        if (state.upcoming.isNotEmpty()) {

            item (span = {
                GridItemSpan(COLUMNS_IN_GRID)
            }){

                HomeMovieList("Upcoming Movies", posters = state.upcoming.map { it.poster })
            }

        }

        item (span = {
            GridItemSpan(COLUMNS_IN_GRID)
        }){
            Spacer(modifier = Modifier.height(15.dp))
        }

        if (state.popularMovies.isNotEmpty()) {

            item (span = {
                GridItemSpan(COLUMNS_IN_GRID)
            }){

                HomeMovieList("Popular", posters = state.popularMovies.map { it.poster })
            }

        }

        if (state.filteredMovies.isNotEmpty()){
            item (span = {
                GridItemSpan(COLUMNS_IN_GRID)
            }){
                RecommendedForYou(selectedFilterType = state.selectedFilter, onFilterClick = {
                    viewModel.onEvent(HomeEvent.ChangeFitler(it))
                })
            }
        }

        items(state.filteredMovies){

            HomeMoviePoster(it.poster, MoviePosterSize.BIG)

        }


    }

    if (state.isLoading) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            CircularProgressIndicator()
        }
    }


}