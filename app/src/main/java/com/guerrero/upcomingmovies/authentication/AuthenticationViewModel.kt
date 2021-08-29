package com.guerrero.upcomingmovies.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class AuthenticationViewModel: ViewModel() {

    val authenticationState = FirebaseUserLiveData().map { user ->
        if(user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }
}