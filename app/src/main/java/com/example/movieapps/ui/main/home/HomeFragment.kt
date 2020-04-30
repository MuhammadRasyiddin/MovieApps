package com.example.movieapps.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapps.R
import com.example.movieapps.model.Movie
import com.example.movieapps.ui.detail.DetailMovieActivity
import com.example.movieapps.ui.main.MovieGridAdapter
import com.example.movieapps.utils.getJsonFromRaw
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        val mGridAdapter = MovieGridAdapter(
            onClickListener = { movie ->
                val intent = DetailMovieActivity.newIntent(requireContext())
                intent.putExtra(DetailMovieActivity.MOVIE_KEY, movie)
                startActivity(intent)
            }
        )
        recyclerMovie.apply {
            adapter = mGridAdapter
            layoutManager = GridLayoutManager(this.context, 2)
        }

        val movieString: String = requireContext().getJsonFromRaw(R.raw.movie)
        val movieList = Gson().fromJson(movieString, Array<Movie>::class.java).toList()
        mGridAdapter.addList(movieList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
