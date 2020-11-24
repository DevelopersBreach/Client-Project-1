package com.developersbreach.clientproject.view.submissions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.developersbreach.clientproject.auth.AuthenticationState
import com.developersbreach.clientproject.repository.ShivaRepository
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber

class SubmissionsViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = ShivaRepository(application.applicationContext)
    private var viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Unconfined)

    val authenticationState: LiveData<AuthenticationState> = repository.getAuthenticationState()

    private lateinit var _billsQuery: Query
    val billsQuery: Query
        get() = _billsQuery

    init {
        getBillsOfCurrentCustomer()
    }

    private fun getBillsOfCurrentCustomer() {
        viewModelScope.launch {
            val phoneNumber = repository.getCurrentUserPhoneNumber().drop(3)
            _billsQuery = repository.getCurrentCustomerTotalBills(phoneNumber)
            Timber.e("Path == $_billsQuery")
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}