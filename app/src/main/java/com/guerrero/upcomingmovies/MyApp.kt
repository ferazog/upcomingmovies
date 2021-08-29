package com.guerrero.upcomingmovies

import android.app.Application
import com.guerrero.upcomingmovies.authentication.AuthenticationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            viewModel {
                AuthenticationViewModel()
            }
            //single { AuthenticationViewModel() }
        }

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(myModule))
        }
    }
}
