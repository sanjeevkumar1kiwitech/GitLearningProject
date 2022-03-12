package com.example.movieapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.app.MovieApp
import com.example.movieapp.database.entaties.Movie
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.repository.MovieRepository

import com.example.movieapp.viewModel.MovieViewModel
import com.example.movieapp.viewModel.MovieViewModelFactory
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var repository: MovieRepository
    lateinit var movieViewModel: MovieViewModel
    lateinit var binding: ActivityMainBinding

    // Sir Image Load nahi uhai uska url hi nahi ban pa raha tha.....

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // getting repository object
        repository = (application as MovieApp).movieRepository

        binding.button.setOnClickListener {
            // load data from server
            binding.tv.text = "Please wait Movie List Loading"
            movieViewModel = ViewModelProvider(
                this,
                MovieViewModelFactory(repository)
            ).get(MovieViewModel::class.java)
            movieViewModel.quotes.observe(this, Observer {
                sortListAccordingToReleaseDate(it.results)
                setDataInList(it.results)
            })
        }
    }

    // movie list sort according to release date
    fun sortListAccordingToReleaseDate(list: List<Movie>) {
        Collections.sort(list, object : Comparator<Movie?> {
            override fun compare(o1: Movie?, o2: Movie?): Int {
                val formatter = SimpleDateFormat("yyyy-MM-dd")
                try {
                    val date1 = formatter.parse(o1?.release_date).time
                    val date2 = formatter.parse(o2?.release_date).time
                    return if (date1 > date2) 1 else if (date1 < date2) -1 else 0
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
                return 1
            }
        })
    }

    // data set in adapter
    fun setDataInList(list: List<Movie>) {
        binding.tv.visibility = View.GONE
        binding.button.visibility = View.GONE
        binding.rv.visibility = View.VISIBLE
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = MovieAdapter(this, list)
    }

}