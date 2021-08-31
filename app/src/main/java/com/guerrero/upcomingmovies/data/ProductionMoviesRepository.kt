package com.guerrero.upcomingmovies.data

import com.guerrero.upcomingmovies.data.remote.MoviesService
import com.guerrero.upcomingmovies.shared.Movie
import com.guerrero.upcomingmovies.shared.exceptions.ReachedEndPageException
import java.text.SimpleDateFormat
import java.util.*

class ProductionMoviesRepository(
    private val remoteDataSource: MoviesService
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
        return result.toAppMovies().filter {
            isReleaseDateGreaterThanToday(it.releaseDate)
        }
    }

    private fun isReleaseDateGreaterThanToday(text: String): Boolean {
        val releaseDateTime = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .parse(text)
            .time
        return releaseDateTime >= System.currentTimeMillis()
    }
}
