package com.example.movieapps.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.movieapps.R
import com.example.movieapps.model.Genre
import com.example.movieapps.model.Movie
import com.example.movieapps.ui.main.MovieGenreAdapter
import com.example.movieapps.utils.getJsonFromRaw
import com.example.movieapps.utils.movieUtils
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.toolbar_template.*
import java.text.SimpleDateFormat
import java.util.*

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var movie: Movie
    private lateinit var mGenresAdapter: MovieGenreAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setUp()
    }

    private fun setUp() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mGenresAdapter = MovieGenreAdapter(mutableListOf())
        if (intent.hasExtra(MOVIE_KEY)) {
            movie = intent.getParcelableExtra(MOVIE_KEY) as Movie
        } else {
            finish()
        }

        Glide.with(this)
            .load(movieUtils.getImagePoster(movie.poster_path))
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imgMovieDetail)

        recyclerGenreMovie.apply {
            adapter = mGenresAdapter
            layoutManager = FlexboxLayoutManager(this@DetailMovieActivity).apply {
                flexDirection = FlexDirection.ROW
                flexWrap = FlexWrap.WRAP
                justifyContent = JustifyContent.FLEX_START
            }
            addItemDecoration(GenreItemDecoration(3))
        }

        txtTitleMovieDetail.text = movie.title
        txtPopularityDetail.text = movie.popularity
        txtVoteDetail.text = movie.vote_count
        txtRateMovieDetail.text = movie.vote_average + " / 10"

        txtLanguageDetail.text = "Language: " + movie.original_language

        val pattern = "dd MMMM yyyy"
        val toFormat = SimpleDateFormat(pattern, Locale.US)
        val parser = SimpleDateFormat("yyyy-MM-dd")
        val date = parser.parse(movie.release_date)
        val szDate: String = toFormat.format(date)
        txtDateReleaseDetail.text = "Date Released: $szDate"
        txtSynopsisDetail.text = movie.overview

        val movieGenres: List<Int>? = movie.genre_ids
        val genreString: String = getJsonFromRaw(R.raw.geremovie)
        val genreList = Gson().fromJson(genreString, Array<Genre>::class.java).toList()
        val genres = movieGenres?.map { g ->
            genreList.find { it.id == g } as Genre
        }
        mGenresAdapter.addItems(genres)
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
