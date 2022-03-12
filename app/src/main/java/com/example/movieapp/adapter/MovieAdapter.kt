package com.example.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.database.entaties.Movie
import com.example.movieapp.util.AppConstant
import com.example.movieapp.util.DateUtil

class MovieAdapter(var context: Context,var movieList:List<Movie> ) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item_single_row, parent, false)
        return MovieViewHolder(view);
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList.get(position)
        holder.name.text = movie.title
        holder.date.text = DateUtil.getReleaseDate(movie.release_date)
       /* val imgUrl : String  = movie.poster_path
        Glide.with(context).load(AppConstant.IMAGE_BASE_URL + imgUrl).into(holder.image)*/
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.cover_image)
        val name = view.findViewById<TextView>(R.id.movie_name_tv)
        val date = view.findViewById<TextView>(R.id.date_tv)
    }

    override fun getItemCount(): Int {
        return  movieList.size
    }

}