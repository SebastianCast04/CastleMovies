package com.example.movieapp.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.movieapp.home.presentation.components.HomeMovieList

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val state = viewModel.state
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 25.dp)
    ) {

        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(90.dp), contentAlignment = Alignment.Center) {

                Image(
                    painter = painterResource(id = R.drawable.logopp),
                    contentDescription = "Logo",
                    modifier = Modifier.padding(vertical = 30.dp)
                )

            }
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
    }
    if (state.isLoading) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            CircularProgressIndicator()
        }
    }


}