package com.example.movieapp.model

import com.example.movieapp.database.entaties.Movie

data class MovieListResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)