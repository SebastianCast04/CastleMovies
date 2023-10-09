package com.example.movieapp.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp.core.domain.model.FilterType

@Composable
fun RecommendedForYou(
    selectedFilterType: FilterType,
    onFilterClick: (FilterType) -> Unit,
    modifier: Modifier = Modifier
) {

        Column(modifier = modifier.fillMaxWidth()) {


            Titles(title = "You'll Love it")
            Spacer(modifier = Modifier.height(12.dp))
            FilterPillContainer(
                filters = FilterType.values().toList(),
                selectedFilter = selectedFilterType,
                onFilterClick = onFilterClick
            )
        }
}