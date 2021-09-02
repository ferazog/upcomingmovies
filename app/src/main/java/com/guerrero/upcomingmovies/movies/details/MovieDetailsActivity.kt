package com.guerrero.upcomingmovies.movies.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.guerrero.upcomingmovies.R
import com.guerrero.upcomingmovies.databinding.ActivityMovieDetailsBinding
import com.guerrero.upcomingmovies.shared.IMAGES_BASE_URL
import com.guerrero.upcomingmovies.shared.Movie
import com.guerrero.upcomingmovies.shared.PARAM_MOVIE
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    private val viewModel: MovieDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBackNavigation()
        setupAddToWatchlistButton()
        getMovieFromExtras()
        observeMovie()
        observeMovieEvent()
    }

    private fun getMovieFromExtras() {
        val movie = intent.getParcelableExtra<Movie>(PARAM_MOVIE)
        movie ?: return
        viewModel.setMovie(movie)
    }

    private fun observeMovie() {
        viewModel.getMovieDetailsObservable().observe(this, { movie ->
            movie.posterPath?.let { loadPosterInToolbar(it) }
            with(binding) {
                collapsingToolbarLayout.title = movie.title
                overview.text = movie.overview
                releaseDate.text = movie.releaseDate
                popularity.text = movie.popularity.toString()
            }
        })
    }

    private fun loadPosterInToolbar(posterPath: String) {
        Glide
            .with(this)
            .load("$IMAGES_BASE_URL${posterPath}")
            .apply(
                RequestOptions.bitmapTransform(
                    VignetteFilterTransformation()
                )
            )
            .into(binding.toolbarBackground)
    }

    private fun setupBackNavigation() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupAddToWatchlistButton() {
        binding.btnAddToWatchlist.setOnClickListener {
            viewModel.addToWatchlist()
        }
    }

    private fun observeMovieEvent() {
        viewModel.getAddToWatchlistEventObservable().observe(this, { event ->
            when (event) {
                AddToWatchlistEvent.Adding -> {
                    binding.run {
                        btnAddToWatchlist.visibility = View.GONE
                        addingToWatchlistProgressBar.visibility = View.VISIBLE
                    }
                }
                AddToWatchlistEvent.Success -> {
                    binding.run {
                        btnAddToWatchlist.visibility = View.VISIBLE
                        addingToWatchlistProgressBar.visibility = View.GONE
                        Toast.makeText(
                            this@MovieDetailsActivity,
                            R.string.movie_added,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    viewModel.clearAddToWatchlistEvent()
                }
            }
        })
    }
}
