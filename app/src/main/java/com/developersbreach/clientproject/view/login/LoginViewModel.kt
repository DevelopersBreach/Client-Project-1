package com.developersbreach.clientproject.view.login

import android.app.Application
import android.text.Editable
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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

    val authenticationState: LiveData<AuthenticationState> = repository.getAuthenticationState()

    init {
        Timber.e("Initialized")
    }

    fun isValidEmail(
        customerMail: Editable?,
        textInputLayout: TextInputLayout,
    ): String {
        val correctMail = !Strings.isNullOrEmpty(customerMail.toString()) &&
                Patterns.EMAIL_ADDRESS.matcher(customerMail.toString()).matches()

        val context = textInputLayout.context
        if (correctMail) {
            textInputLayout.isErrorEnabled = false
        } else {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = context.getString(R.string.invalid_mail_error_text)
        }

        return customerMail.toString()
    }

    fun isValidateUsername(
        text: Editable?,
        textInputLayout: TextInputLayout,
    ): String {
        val context = textInputLayout.context
        if (text?.isEmpty()!!) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = context.getString(R.string.field_required_error_text)
        } else if (text.isNotEmpty()) {
            textInputLayout.isErrorEnabled = false
        }

        return text.toString()
    }

    fun isValidPhoneNumber(
        customerContact: Editable?,
        textInputLayout: TextInputLayout,
    ): String {
        val context = textInputLayout.context
        when {
            customerContact?.isEmpty()!! -> {
                textInputLayout.isErrorEnabled = true
                textInputLayout.error = context.getString(R.string.field_required_error_text)
            }
            customerContact.length != 10 -> {
                textInputLayout.isErrorEnabled = true
                textInputLayout.error = "Invalid"
            }
            !customerContact.isNullOrEmpty() || customerContact.length == 10 -> {
                textInputLayout.isErrorEnabled = false
                textInputLayout.error = null
            }
        }

        return customerContact.toString()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}