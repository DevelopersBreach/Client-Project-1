package com.developersbreach.clientproject.view.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.developersbreach.clientproject.model.Account
import com.developersbreach.clientproject.model.Submission

class DetailViewModelFactory internal constructor(
    private val application: Application,
    private val submission: Submission?,
    private val account: Account?
) : ViewModelProvider.AndroidViewModelFactory(application) {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(application, submission, account) as T
        }
        throw IllegalArgumentException("Cannot create Instance for DetailViewModel class")
    }
}