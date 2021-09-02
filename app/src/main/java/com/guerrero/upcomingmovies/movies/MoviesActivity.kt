package com.guerrero.upcomingmovies.movies

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.guerrero.upcomingmovies.R
import com.guerrero.upcomingmovies.authentication.AuthenticationActivity
import com.guerrero.upcomingmovies.authentication.AuthenticationState
import com.guerrero.upcomingmovies.authentication.AuthenticationViewModel
import com.guerrero.upcomingmovies.databinding.ActivityMoviesBinding
import com.guerrero.upcomingmovies.shared.setupDailyAlarm
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding

    private val authenticationViewModel: AuthenticationViewModel by viewModel()

    private val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        findNavController(R.id.nav_host_fragment).let { navController ->
            NavigationUI.setupActionBarWithNavController(
                this,
                navController
            )
        }
        observeAuthState()
        setupDailyAlarm(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }

    private fun observeAuthState() {
        authenticationViewModel.authenticationState.observe(this, { state ->
            if (state == AuthenticationState.UNAUTHENTICATED) {
                startActivity(
                    Intent(this, AuthenticationActivity::class.java)
                )
                finish()
            }
        })
    }
}
