package com.guerrero.upcomingmovies.authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.guerrero.upcomingmovies.R
import com.guerrero.upcomingmovies.movies.MainActivity
import com.guerrero.upcomingmovies.shared.SIGN_IN_RESULT_CODE
import com.guerrero.upcomingmovies.shared.getTag
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthenticationActivity : AppCompatActivity() {

    private val authenticationViewModel: AuthenticationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeAuthenticationState()
    }

    private fun observeAuthenticationState() {
        authenticationViewModel.authenticationState.observe(this, { state ->
            when (state) {
                AuthenticationState.AUTHENTICATED -> navigateToWatchlistFragment()
                else -> launchSignInFlow()
            }
        })
    }

    private fun launchSignInFlow() {
        val customLayout = AuthMethodPickerLayout
            .Builder(R.layout.login_layout)
            .setGoogleButtonId(R.id.btnSignInGoogle)
            .setEmailButtonId(R.id.btnSignInEmail)
            .build()

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        val signInIntent = AuthUI.getInstance().createSignInIntentBuilder()
            .setTheme(R.style.Theme_UpcomingMovies)
            .setAuthMethodPickerLayout(customLayout)
            .setAvailableProviders(providers)
            .build()

        startActivityForResult(
            signInIntent,
            SIGN_IN_RESULT_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_RESULT_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                navigateToWatchlistFragment()
            } else {
                Log.i(getTag(), "Sign in unsuccessful: ${response?.error?.errorCode}")
            }
        }
    }

    private fun navigateToWatchlistFragment() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
