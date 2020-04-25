package com.example.movieapps.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.movieapps.R
import com.example.movieapps.model.Movie
import com.example.movieapps.utils.movieUtils
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieGridAdapter(
    private val movieList: MutableList<Movie> = mutableListOf()
//    private val onClickListener : (Movie) -> Unit
) : RecyclerView.Adapter<MovieGridAdapter.MovieGridViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGridViewHolder {
        return MovieGridViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieGridViewHolder, position: Int) {
        val movie: Movie = movieList[position]
        holder.bind(movie)
    }

    inner class MovieGridViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun loadImage(url: String) {
            Glide.with(view)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerInside()
                .into(view.imgMovie)
        }

        fun bind(movie: Movie) {
            loadImage(movieUtils.getImagePoster(movie.poster_path))
            view.txtTitleMovie.text = movie.title
            view.TxtRateMovie.text = "Rate : " + movie.vote_average
//            view.setOnClickListener {
//                onClickListener(movie)
//            }
        }
    }

    fun addList(list: List<Movie>?) {
        list?.let { movie ->
            movieList.addAll(movie)
        }
    }
}