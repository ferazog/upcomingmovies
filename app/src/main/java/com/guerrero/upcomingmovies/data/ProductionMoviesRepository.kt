package com.guerrero.upcomingmovies.data

import com.guerrero.upcomingmovies.data.local.MovieEntity
import com.guerrero.upcomingmovies.data.local.MoviesDao
import com.guerrero.upcomingmovies.data.remote.MoviesService
import com.guerrero.upcomingmovies.shared.Movie
import com.guerrero.upcomingmovies.shared.exceptions.ReachedEndPageException
import java.text.SimpleDateFormat
import java.util.*

class ProductionMoviesRepository(
    private val remoteDataSource: MoviesService,
    private val localDataSource: MoviesDao
) : MoviesRepository {

    private var totalPages = 0

    override suspend fun getUpcomingList(page: Int): List<Movie> {
        if (totalPages > 0 && page > totalPages) {
            throw ReachedEndPageException()
        }
        val result = remoteDataSource.getUpcomingList(page = page)
        if (totalPages == 0) {
            totalPages = result.totalPages
        }
        return result.toAppMovies()
    }

    override suspend fun addToWatchlist(movie: Movie) {
        localDataSource.insertMovie(MovieEntity(movie))
    }

    override suspend fun getWatchlist(): List<Movie> {
        return localDataSource.getWatchlist().map {
            it.toMovie()
        }
    }

    override suspend fun getWatchlistMoviesForToday(): List<Movie> {
        return localDataSource.getWatchlist().filter {
            isReleasingTodayOrBefore(it.releaseDate)
        }.map {
            it.toMovie()
        }
    }

    private fun isReleasingTodayOrBefore(text: String): Boolean {
        val releaseDateTime = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .parse(text)
            .time
        return releaseDateTime <= System.currentTimeMillis()
    }
}
