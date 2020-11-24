package com.developersbreach.clientproject.view.account

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.developersbreach.clientproject.model.Account

class AccountViewModelFactory internal constructor(
    private val application: Application,
    private val account: Account
) : ViewModelProvider.AndroidViewModelFactory(application) {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            return AccountViewModel(application, account) as T
        }
        throw IllegalArgumentException("Cannot create Instance for AccountViewModel class")
    }
}