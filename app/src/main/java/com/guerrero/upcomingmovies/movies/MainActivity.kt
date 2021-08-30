package com.guerrero.upcomingmovies.movies

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.guerrero.upcomingmovies.authentication.AuthenticationActivity
import com.guerrero.upcomingmovies.authentication.AuthenticationState
import com.guerrero.upcomingmovies.authentication.AuthenticationViewModel
import com.guerrero.upcomingmovies.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val authenticationViewModel: AuthenticationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeAuthState()
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
