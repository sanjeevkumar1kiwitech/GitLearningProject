package com.example.movieapp.repository



import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.model.MovieListResponse
import com.example.movieapp.database.AppDataBase
import com.example.movieapp.retrofit.APIServiceInterface
import com.example.movieapp.util.AppConstant
import com.example.movieapp.util.NetworkUtils


class MovieRepository(
    private val apiServiceInterface: APIServiceInterface,
    private val db: AppDataBase,
    private val applicationContext: Context
) {


    private val moviesLiveData = MutableLiveData<MovieListResponse>()
    val movies: LiveData<MovieListResponse>
        get() = moviesLiveData


    suspend fun getMovies(){

        if(NetworkUtils.isInternetAvailable(applicationContext)){
            val result = apiServiceInterface.getMovieList(AppConstant.SORT_BY,AppConstant.API_KEY)
            if(result?.body() != null){
                db.movieDao().addMovies(result.body()!!.results)
                moviesLiveData.postValue(result.body())
            }
        }else{
            val movie =  db.movieDao().getMovies()
            moviesLiveData.postValue(MovieListResponse(1,movie,1,1))
        }
    }

}




    /*suspend fun insert(movie: Movie){
        movieDAO.insertMovie(movie)
    }

    fun getMovieList():LiveData<List<Movie>> {
       return movieDAO.getMovieList()
    }*/
