package com.example.movieapps.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapps.R
import com.example.movieapps.model.Movie
import com.example.movieapps.utils.getJsonFromRaw
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_template.toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()
    }

    private fun setUp() {
        setSupportActionBar(toolbar)
        val mGridAdapter = MovieGridAdapter()
        recyclerMovie.apply {
            adapter = mGridAdapter
            layoutManager = GridLayoutManager(this.context, 2)
        }

        val movieString: String = getJsonFromRaw(R.raw.movie)
        val movieList = Gson().fromJson(movieString, Array<Movie>::class.java).toList()
        mGridAdapter.addList(movieList)
    }
}
