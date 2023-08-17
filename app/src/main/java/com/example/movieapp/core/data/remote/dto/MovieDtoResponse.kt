package com.example.movieapp.core.data.remote.dto

import com.squareup.moshi.Json

data class MovieDtoResponse(
    @field:Json(name = "dates")
    val dates: Dates?,
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "results")
    val results: List<MovieResult>,
    @field:Json(name = "total_pages")
    val total_pages: Int,
    @field:Json(name = "total_results")
    val total_results: Int
)