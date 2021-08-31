package com.guerrero.upcomingmovies.shared

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Long,
    val title: String,
    val releaseDate: String,
    val posterPath: String?,
    val overview: String,
    val popularity: Double
) : Parcelable
