package com.example.movieapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.MovieListResponse
import com.example.movieapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(val movieRepository: MovieRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getMovies()
        }
    }

    val quotes: LiveData<MovieListResponse>
        get() = movieRepository.movies
}

