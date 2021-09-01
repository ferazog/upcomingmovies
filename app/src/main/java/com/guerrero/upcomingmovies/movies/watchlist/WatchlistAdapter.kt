package com.guerrero.upcomingmovies.movies.watchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.guerrero.upcomingmovies.databinding.WatchlistMovieLayoutBinding
import com.guerrero.upcomingmovies.movies.MovieClickListener
import com.guerrero.upcomingmovies.shared.Movie

class WatchlistAdapter(
    private val listener: MovieClickListener
) : ListAdapter<Movie, WatchlistItemViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchlistItemViewHolder {
        return WatchlistItemViewHolder(
            WatchlistMovieLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WatchlistItemViewHolder, position: Int) {
        holder.bind(
            getItem(position),
            listener
        )
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}
