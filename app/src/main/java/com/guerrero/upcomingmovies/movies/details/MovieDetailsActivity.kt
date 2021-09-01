package com.guerrero.upcomingmovies.movies.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.guerrero.upcomingmovies.databinding.ActivityMovieDetailsBinding
import com.guerrero.upcomingmovies.shared.PARAM_MOVIE_ID

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val arg = intent.getLongExtra(PARAM_MOVIE_ID, 0)
        binding.textview.text = arg.toString()
    }
}
