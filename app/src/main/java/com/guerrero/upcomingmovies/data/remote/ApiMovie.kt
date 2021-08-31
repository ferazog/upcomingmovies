package com.guerrero.upcomingmovies.data.remote

import com.google.gson.annotations.SerializedName
import com.guerrero.upcomingmovies.shared.Movie

data class ApiMovie(
    @SerializedName("id")
    private val id: Long,
    @SerializedName("title")
    private val title: String,
    @SerializedName("release_date")
    private val releaseDate: String,
    @SerializedName("poster_path")
    private val posterPath: String?,
    @SerializedName("overview")
    private val overview: String,
    @SerializedName("popularity")
    private val popularity: Double
) {

    fun toMovie() = Movie(
        id,
        title,
        releaseDate,
        posterPath,
        overview,
        popularity
    )
}
