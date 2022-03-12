package com.example.movieapp.retrofit

import com.example.movieapp.model.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServiceInterface {



    @GET("movie")
    suspend fun getMovieList(
        @Query("sort_by") sort_by:String,
        @Query("api_key") api_key:String
    ):Response<MovieListResponse>


    //https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=b1f5db0adfa30b9e8714dba42e9edb44

}