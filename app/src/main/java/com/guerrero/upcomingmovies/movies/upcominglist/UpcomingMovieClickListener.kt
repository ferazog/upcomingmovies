package com.guerrero.upcomingmovies.movies.upcominglist

import com.guerrero.upcomingmovies.shared.Movie

interface UpcomingMovieClickListener {

    fun onUpcomingMovieClicked(movie: Movie)
}
