package com.guerrero.upcomingmovies.data

import com.guerrero.upcomingmovies.data.remote.MoviesService
import com.guerrero.upcomingmovies.shared.Movie

class ProductionMoviesRepository(
    private val remoteDataSource: MoviesService
) : MoviesRepository {

    override suspend fun getUpcomingList(apiKey: String): List<Movie> {
        return remoteDataSource.getUpcomingList().toAppMovies()
    }
}
