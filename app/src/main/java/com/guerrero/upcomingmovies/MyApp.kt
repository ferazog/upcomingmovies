package com.guerrero.upcomingmovies

import android.app.Application
import androidx.room.Room
import com.guerrero.upcomingmovies.authentication.AuthenticationViewModel
import com.guerrero.upcomingmovies.data.MoviesRepository
import com.guerrero.upcomingmovies.data.ProductionMoviesRepository
import com.guerrero.upcomingmovies.data.local.MoviesDatabase
import com.guerrero.upcomingmovies.data.remote.MoviesService
import com.guerrero.upcomingmovies.movies.MoviesViewModel
import com.guerrero.upcomingmovies.shared.BASE_URL
import com.guerrero.upcomingmovies.shared.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            viewModel { AuthenticationViewModel() }
            viewModel {
                MoviesViewModel(
                    repository = get()
                )
            }
            single {
                Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            single {
                Room.databaseBuilder(
                    get(),
                    MoviesDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            factory {
                ProductionMoviesRepository(
                    (get() as Retrofit).create(MoviesService::class.java),
                    (get() as MoviesDatabase).moviesDao
                ) as MoviesRepository
            }
        }

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(myModule))
        }
    }
}
