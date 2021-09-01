package com.guerrero.upcomingmovies.movies.watchlist

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guerrero.upcomingmovies.R
import com.guerrero.upcomingmovies.databinding.WatchlistMovieLayoutBinding
import com.guerrero.upcomingmovies.movies.MovieClickListener
import com.guerrero.upcomingmovies.shared.IMAGES_BASE_URL
import com.guerrero.upcomingmovies.shared.Movie

class WatchlistItemViewHolder(
    private val binding: WatchlistMovieLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie, listener: MovieClickListener) {
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
            listener.onMovieClicked(movie)
        }
    }
}
