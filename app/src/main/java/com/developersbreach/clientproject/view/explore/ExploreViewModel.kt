package com.developersbreach.clientproject.view.explore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.developersbreach.clientproject.model.Account
import com.developersbreach.clientproject.repository.ShivaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ExploreViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = ShivaRepository(application.applicationContext)
    private var viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(
        viewModelJob + Dispatchers.Unconfined
    )

    private var _account = MutableLiveData<Account>()
    val account: LiveData<Account>
        get() = _account

    init {
        viewModelScope.launch {
            getAccountDetails()
        }
    }

    private suspend fun getAccountDetails() {
        val phoneNumber: String = repository.getCurrentUserPhoneNumber().drop(3)
        repository.getUserAccount().document(phoneNumber).get()
            .addOnSuccessListener { docSnapshot ->
                _account.value = docSnapshot?.toObject(Account::class.java)
            }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}