package com.guerrero.upcomingmovies.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.guerrero.upcomingmovies.data.MoviesRepository
import com.guerrero.upcomingmovies.shared.sendNotification
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.inject
import kotlin.coroutines.CoroutineContext

class AlarmReceiver : BroadcastReceiver(), CoroutineScope {

    private val repository: MoviesRepository by inject(MoviesRepository::class.java)

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + Job()

    override fun onReceive(context: Context?, intent: Intent?) {
        context ?: return
        CoroutineScope(coroutineContext).launch(SupervisorJob()) {
            val moviesForToday = repository.getWatchlistMoviesForToday()
            moviesForToday.forEach {
                sendNotification(context, it)
            }
        }
    }
}
