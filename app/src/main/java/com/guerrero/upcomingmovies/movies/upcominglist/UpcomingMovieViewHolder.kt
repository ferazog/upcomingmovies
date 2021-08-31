package com.guerrero.upcomingmovies.movies.upcominglist

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guerrero.upcomingmovies.R
import com.guerrero.upcomingmovies.databinding.UpcomingMovieLayoutBinding
import com.guerrero.upcomingmovies.shared.IMAGES_BASE_URL
import com.guerrero.upcomingmovies.shared.Movie

class UpcomingMovieViewHolder(
    private val binding: UpcomingMovieLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie, listener: UpcomingMovieClickListener) {
        if (movie.posterPath != null) {
            Glide
                .with(itemView.context)
                .load("$IMAGES_BASE_URL${movie.posterPath}")
                .placeholder(R.drawable.ic_baseline_local_movies)
                .into(binding.poster)
        }
        with(binding) {
            title.text = movie.title
            releaseDate.text = movie.releaseDate
        }

        binding.root.setOnClickListener {
            listener.onUpcomingMovieClicked(movie)
        }
    }
}