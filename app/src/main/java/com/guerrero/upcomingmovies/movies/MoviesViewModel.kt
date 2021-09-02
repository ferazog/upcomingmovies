package com.guerrero.upcomingmovies.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guerrero.upcomingmovies.data.MoviesRepository
import com.guerrero.upcomingmovies.movies.upcominglist.UpcomingListEvent
import com.guerrero.upcomingmovies.shared.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val repository: MoviesRepository
) : ViewModel() {

    private val watchList = MutableLiveData<List<Movie>>().apply {
        value = listOf()
    }

    private val upcomingList = MutableLiveData<List<Movie>>().apply {
        value = listOf()
    }

    private val upcomingListEvent = MutableLiveData<UpcomingListEvent>().apply {
        value = UpcomingListEvent.Normal
    }

    fun getWatchListObservable(): LiveData<List<Movie>> = watchList

    fun getUpcomingListObservable(): LiveData<List<Movie>> = upcomingList

    fun getUpcomingListEventObservable(): LiveData<UpcomingListEvent> = upcomingListEvent

    private var requestPage = 1

    init {
        requestUpcomingList()
    }

    fun requestUpcomingList() {
        upcomingListEvent.value = UpcomingListEvent.Loading
        viewModelScope.launch(dispatcher) {
            try {
                upcomingList.postValue(repository.getUpcomingList(1))
                upcomingListEvent.postValue(UpcomingListEvent.Normal)
            } catch (exception: Exception) {
                upcomingListEvent.postValue(UpcomingListEvent.Failed(exception.message.toString()))
                exception.printStackTrace()
            }
        }
    }

    fun loadMoreUpcomingMovies() {
        requestPage += 1
        viewModelScope.launch(dispatcher) {
            try {
                val oldUpcomingList = upcomingList.value ?: listOf()
                upcomingList.postValue(oldUpcomingList.plus(repository.getUpcomingList(requestPage)))
            } catch (exception: Exception) {
                upcomingListEvent.postValue(UpcomingListEvent.Failed(exception.message.toString()))
                exception.printStackTrace()
            }
        }
    }

    fun getWatchlist() {
        viewModelScope.launch(dispatcher) {
            watchList.postValue(repository.getWatchlist())
        }
    }
}
