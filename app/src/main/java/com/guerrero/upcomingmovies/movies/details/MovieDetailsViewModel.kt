package com.guerrero.upcomingmovies.movies.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guerrero.upcomingmovies.data.MoviesRepository
import com.guerrero.upcomingmovies.shared.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val repository: MoviesRepository
) : ViewModel() {

    private val movieDetails = MutableLiveData<Movie>()

    private val addToWatchlistEvent = MutableLiveData<AddToWatchlistEvent>().apply {
        value = AddToWatchlistEvent.Normal
    }

    fun getMovieDetailsObservable(): LiveData<Movie> = movieDetails

    fun getAddToWatchlistEventObservable(): LiveData<AddToWatchlistEvent> = addToWatchlistEvent

    fun addToWatchlist() {
        val movie = movieDetails.value ?: return
        addToWatchlistEvent.value = AddToWatchlistEvent.Adding
        viewModelScope.launch(dispatcher) {
            repository.addToWatchlist(movie)
            addToWatchlistEvent.postValue(AddToWatchlistEvent.Success)
        }
    }

    fun clearAddToWatchlistEvent() {
        addToWatchlistEvent.value = AddToWatchlistEvent.Normal
    }

    fun setMovie(movie: Movie) {
        movieDetails.value = movie
    }
}
