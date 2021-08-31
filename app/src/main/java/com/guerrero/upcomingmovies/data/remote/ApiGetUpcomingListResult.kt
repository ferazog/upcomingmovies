package com.guerrero.upcomingmovies.data

import com.google.gson.annotations.SerializedName
import com.guerrero.upcomingmovies.data.remote.ApiMovie
import com.guerrero.upcomingmovies.shared.Movie

data class ApiGetUpcomingListResult(
    @SerializedName("results")
    private val results: List<ApiMovie>,
    @SerializedName("total_pages")
    val totalPages: Int
) {

    fun toAppMovies(): List<Movie> {
        return results.map {
            it.toMovie()
        }
    }
}
