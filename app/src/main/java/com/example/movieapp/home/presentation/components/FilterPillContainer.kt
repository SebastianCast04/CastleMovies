package com.example.movieapp.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp.home.presentation.FilterType

@Composable
fun FilterPillContainer(
    filters: List<FilterType>,
    selectedFilter: FilterType,
    modifier: Modifier = Modifier,
    onFilterClick: (FilterType) -> Unit
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filters){

            FilterBoxes(type = it, isSelected = selectedFilter == it) {

                onFilterClick(it)
            }
        }
    }
}