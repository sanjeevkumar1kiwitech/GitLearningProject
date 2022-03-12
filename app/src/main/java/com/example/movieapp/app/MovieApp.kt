package com.example.movieapp.app

import android.app.Application
import com.example.movieapp.database.AppDataBase
import com.example.movieapp.repository.MovieRepository
import com.example.movieapp.retrofit.APIServiceInterface
import com.example.movieapp.retrofit.ApiClient

class MovieApp: Application() {

    lateinit var movieRepository: MovieRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val db = AppDataBase.getDatabase(applicationContext);
        val apiClient = ApiClient.getInstance().create(APIServiceInterface::class.java)
        movieRepository = MovieRepository(apiClient,db,applicationContext)
    }
}