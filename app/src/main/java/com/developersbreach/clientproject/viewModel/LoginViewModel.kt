package com.developersbreach.clientproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.developersbreach.clientproject.auth.FirebaseUserLiveData
import com.developersbreach.clientproject.auth.AuthenticationState

class LoginViewModel : ViewModel() {

    val authenticationState = FirebaseUserLiveData().map { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }
}