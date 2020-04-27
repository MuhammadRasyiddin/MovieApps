package com.example.movieapps.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.movieapps.R
import com.example.movieapps.model.Movie
import com.example.movieapps.utils.movieUtils
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.toolbar_template.*

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var movie: Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setUp()
    }

    private fun setUp() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (intent.hasExtra(MOVIE_KEY)) {
            movie = intent.getParcelableExtra(MOVIE_KEY) as Movie
        } else {
            finish()
        }


        Glide.with(this)
            .load(movieUtils.getImagePoster(movie.poster_path))
            .centerInside()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imgMovieDetail)

        txtTitleMovieDetail.text = movie.title
        txtPopularityDetail.text = "Popularity: " + movie.popularity
        txtVoteDetail.text = "Voted User: " + movie.vote_count
        txtRateMovieDetail.text = "Rate: " + movie.vote_average + "/10"
        txtGenreDetail.text = "Genre: " + movie.genre_ids.toString()
        txtLanguageDetail.text = "Language: " + movie.original_language
        txtDateReleaseDetail.text = "Date Released: " + movie.release_date
        txtSynopsisDetail.text = movie.overview
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, DetailMovieActivity::class.java)
        }

        const val MOVIE_KEY = "MOVIE_KEY"
    }

}
