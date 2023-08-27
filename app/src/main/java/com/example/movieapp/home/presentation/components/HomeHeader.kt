package com.example.movieapp.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.R

@Composable
fun HomeHeader(){

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