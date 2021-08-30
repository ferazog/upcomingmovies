package com.guerrero.upcomingmovies

import android.app.Application
import com.guerrero.upcomingmovies.authentication.AuthenticationViewModel
import com.guerrero.upcomingmovies.data.ProductionMoviesRepository
import com.guerrero.upcomingmovies.data.remote.MoviesService
import com.guerrero.upcomingmovies.movies.MoviesViewModel
import com.guerrero.upcomingmovies.shared.BASE_URL
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            viewModel { AuthenticationViewModel() }
            viewModel {
                MoviesViewModel(
                    repository = ProductionMoviesRepository(
                        (get() as Retrofit).create(
                            MoviesService::class.java
                        )
                    )
                )
            }
            single {
                Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        }

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(myModule))
        }
    }
}
