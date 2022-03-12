package com.example.mvvm_skeleton_demo.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieapp.database.entaties.Movie

@Dao
interface MovieDAO {

    @Insert
    suspend fun addMovies(list: List<Movie>)

    @Query("Select * from movie_table") // in case of Livedata room automatic run in background thread
    fun getMovies(): List<Movie>
}