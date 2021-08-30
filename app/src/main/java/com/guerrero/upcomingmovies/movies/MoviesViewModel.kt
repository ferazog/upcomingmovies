package com.guerrero.upcomingmovies.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.guerrero.upcomingmovies.data.MoviesRepository
import com.guerrero.upcomingmovies.movies.upcominglist.UpcomingListEvent
import com.guerrero.upcomingmovies.shared.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

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

    fun requestUpcomingList(apiKey: String) {
        upcomingListEvent.value = UpcomingListEvent.Loading
        viewModelScope.launch(dispatcher) {
            try {
                upcomingList.postValue(repository.getUpcomingList(apiKey))
                upcomingListEvent.postValue(UpcomingListEvent.Normal)
            } catch (exception: Exception) {
                upcomingListEvent.postValue(UpcomingListEvent.Failed(exception.message.toString()))
                exception.printStackTrace()
            }
        }
    }
}
