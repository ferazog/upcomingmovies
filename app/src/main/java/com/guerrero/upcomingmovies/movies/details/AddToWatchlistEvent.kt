 package com.guerrero.upcomingmovies.movies.details

sealed class AddToWatchlistEvent {

    object Normal: AddToWatchlistEvent()

    object Adding: AddToWatchlistEvent()

    object Success: AddToWatchlistEvent()
}
