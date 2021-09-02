package com.guerrero.upcomingmovies.movies.upcominglist

sealed class UpcomingListEvent {

    object Loading : UpcomingListEvent()

    object Normal : UpcomingListEvent()

    class Failed(val message: String) : UpcomingListEvent()
}
