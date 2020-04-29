package com.example.movieapps.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapps.R
import com.example.movieapps.model.Genre
import kotlinx.android.synthetic.main.item_genre.view.*

class MovieGenreAdapter(
    private val movieGenreList: MutableList<Genre> = mutableListOf()
) : RecyclerView.Adapter<MovieGenreAdapter.MovieGenreViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieGenreAdapter.MovieGenreViewHolder {
        return MovieGenreViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movieGenreList.size
    }

    override fun onBindViewHolder(holder: MovieGenreAdapter.MovieGenreViewHolder, position: Int) {
        holder.bind(movieGenreList[position])
    }

    fun addItems(data: List<Genre>?) {
        movieGenreList.clear()
        data?.let {

            movieGenreList.addAll(it)
        }
        notifyDataSetChanged()
    }

    inner class MovieGenreViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(genre: Genre) {
            view.txtGenreDetail.text = genre.name
        }

    }

}