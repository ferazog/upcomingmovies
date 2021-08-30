package com.guerrero.upcomingmovies.movies.upcominglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.guerrero.upcomingmovies.databinding.UpcomingMovieLayoutBinding
import com.guerrero.upcomingmovies.shared.Movie

class UpcomingListAdapter(
    val listener: UpcomingMovieClickListener
) : ListAdapter<Movie, UpcomingMovieViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMovieViewHolder {
        return UpcomingMovieViewHolder(
            UpcomingMovieLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UpcomingMovieViewHolder, position: Int) {
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
