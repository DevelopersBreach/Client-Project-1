package com.developersbreach.clientproject.view.submissions

import android.app.Application
import android.text.Editable
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.auth.AuthenticationState
import com.developersbreach.clientproject.repository.ShivaRepository
import com.google.android.material.textfield.TextInputLayout
import com.google.common.base.Strings.isNullOrEmpty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber

class SubmissionsViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = ShivaRepository(application.applicationContext)
    private var viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Unconfined)

    val authenticationState: LiveData<AuthenticationState> = repository.getAuthenticationState()

    init {
        Timber.e("Initialized")
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}