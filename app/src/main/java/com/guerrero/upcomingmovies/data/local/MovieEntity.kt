package com.guerrero.upcomingmovies.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.guerrero.upcomingmovies.shared.Movie

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val releaseDate: String,
    val posterPath: String?,
    val overview: String,
    val popularity: Double
) {

    constructor(movie: Movie) : this(
        movie.id,
        movie.title,
        movie.releaseDate,
        movie.posterPath,
        movie.overview,
        movie.popularity
    )

    fun toMovie() = Movie(
        id,
        title,
        releaseDate,
        posterPath,
        overview,
        popularity
    )
}
