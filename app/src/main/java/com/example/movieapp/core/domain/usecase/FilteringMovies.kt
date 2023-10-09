package com.example.movieapp.core.domain.usecase

import com.example.movieapp.core.domain.model.Movie

class FilteringMovies {

    operator fun invoke(movies:List<Movie>):List<Movie>{
        val maxFilters = if(movies.size < 6) movies.size else 6
        return movies.subList(0, maxFilters)
    }
}