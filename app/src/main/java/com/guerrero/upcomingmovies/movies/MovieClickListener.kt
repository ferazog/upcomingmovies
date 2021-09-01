package com.guerrero.upcomingmovies.movies

import com.guerrero.upcomingmovies.shared.Movie

interface MovieClickListener {

    fun onMovieClicked(movie: Movie)
}
