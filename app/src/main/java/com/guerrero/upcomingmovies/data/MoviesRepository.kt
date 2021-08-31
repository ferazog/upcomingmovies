package com.guerrero.upcomingmovies.data

import com.guerrero.upcomingmovies.shared.Movie

interface MoviesRepository {

    suspend fun getUpcomingList(page: Int): List<Movie>
}
