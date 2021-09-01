package com.guerrero.upcomingmovies.data

import com.guerrero.upcomingmovies.shared.Movie

interface MoviesRepository {

    suspend fun getUpcomingList(page: Int): List<Movie>

    suspend fun addToWatchlist(movie: Movie)

    suspend fun getWatchlist(): List<Movie>

    suspend fun getWatchlistMoviesForToday(): List<Movie>
}
