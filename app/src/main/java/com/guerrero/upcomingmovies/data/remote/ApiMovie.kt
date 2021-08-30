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
    private val posterPath: String
) {

    fun toMovie() = Movie(
        id,
        title,
        releaseDate,
        posterPath
    )
}
