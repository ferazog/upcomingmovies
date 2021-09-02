package com.guerrero.upcomingmovies.shared

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.guerrero.upcomingmovies.R
import com.guerrero.upcomingmovies.movies.details.MovieDetailsActivity
import com.guerrero.upcomingmovies.notifications.AlarmReceiver
import java.util.*

fun Activity.getTag() = this::class.simpleName

fun sendNotification(context: Context, movie: Movie) {
    val notificationManager = context.getSystemService(
        Context.NOTIFICATION_SERVICE
    ) as NotificationManager

    if (
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
        && notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID) == null
    ) {
        val name = context.getString(R.string.app_name)
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            name,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
    }

    val intent = Intent(context.applicationContext, MovieDetailsActivity::class.java).apply {
        putExtra(PARAM_MOVIE, movie)
    }
    val stackBuilder = TaskStackBuilder.create(context)
        .addParentStack(MovieDetailsActivity::class.java)
        .addNextIntent(intent)
    val notificationPendingIntent = stackBuilder
        .getPendingIntent(getUniqueId(), PendingIntent.FLAG_UPDATE_CURRENT)

    val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle(movie.title)
        .setContentText(context.getString(R.string.releasing))
        .setContentIntent(notificationPendingIntent)
        .setAutoCancel(true)
        .build()

    notificationManager.notify(getUniqueId(), notification)
}

private fun getUniqueId() = ((System.currentTimeMillis() % 10000).toInt())

fun setupDailyAlarm(context: Context) {
    val intent = Intent(context, AlarmReceiver::class.java)
    val alarmPendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    // Set the alarm to start at approximately 6:00 a.m.
    val calendar: Calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.HOUR_OF_DAY, 6)
    }

    with(context.getSystemService(Context.ALARM_SERVICE) as AlarmManager) {
        setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            alarmPendingIntent
        )
    }
}
