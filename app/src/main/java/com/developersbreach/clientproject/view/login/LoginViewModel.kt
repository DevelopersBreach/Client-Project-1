package com.developersbreach.clientproject.view.login

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.auth.AuthenticationState
import com.developersbreach.clientproject.repository.ShivaRepository
import com.google.android.material.textfield.TextInputLayout
import com.google.common.base.Strings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber

class LoginViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = ShivaRepository(application.applicationContext)
    private var viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Unconfined)

    val firebaseAuth = repository.getFirebaseAuth()
    val authenticationState: LiveData<AuthenticationState> = repository.getAuthenticationState()

    init {
        Timber.e("Initialized")
    }

    private fun correctMail(email: String): Boolean {
        return !Strings.isNullOrEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidEmail(
        email: String,
        textInputLayout: TextInputLayout,
    ): String {

        val context = textInputLayout.context
        if (correctMail(email)) {
            textInputLayout.isErrorEnabled = false
        } else {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = context.getString(R.string.invalid_mail_error_text)
        }

        return email
    }

    fun isValidUsername(
        username: String,
        textInputLayout: TextInputLayout,
    ): String {
        val context = textInputLayout.context
        if (username.isEmpty()) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = context.getString(R.string.field_required_error_text)
        } else if (username.isNotEmpty() && username.length < 3) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = context.getString(R.string.field_length_error_text)
        } else if (username.isNotEmpty() && username.length > 2) {
            textInputLayout.isErrorEnabled = false
        }

        return username
    }

    fun isValidPhoneNumber(
        phoneNumber: String,
        textInputLayout: TextInputLayout,
    ): String {
        val context = textInputLayout.context
        if (phoneNumber.isEmpty()) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = context.getString(R.string.field_required_error_text)
        } else if (phoneNumber.isNotEmpty() && phoneNumber.length != 10) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = context.getString(R.string.invalid_phone_number)
        } else if (phoneNumber.isNotEmpty() || phoneNumber.length == 10) {
            textInputLayout.isErrorEnabled = false
        }

        return phoneNumber
    }

    fun validateAllTextFields(
        username: String, email: String, phoneNumber: String
    ): LiveData<Boolean> {

        val fieldsAreValidated: MutableLiveData<Boolean> = MutableLiveData()
        val validSubmit: Boolean = username.isNotEmpty() && username.length > 2 &&
                email.isNotEmpty() && correctMail(email) &&
                phoneNumber.isNotEmpty() && phoneNumber.length == 10

        fieldsAreValidated.value = validSubmit
        return fieldsAreValidated
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    companion object {
        const val KEY_VERIFY_IN_PROGRESS = "key_verify_progress"
    }
}