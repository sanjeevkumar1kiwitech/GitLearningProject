package com.example.movieapp.retrofit

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val BASE_URL : String = "https://api.themoviedb.org/3/discover/"
     fun getInstance():Retrofit{
        val retrofit = Retrofit.Builder()
             .baseUrl(BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
         return retrofit;
     }
}