package com.developersbreach.clientproject.view.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.developersbreach.clientproject.model.Customers

class DetailViewModelFactory internal constructor(
    private val application: Application,
    private val customers: Customers,
    private val photoUrl: String,
) : ViewModelProvider.AndroidViewModelFactory(application) {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(application, customers, photoUrl) as T
        }
        throw IllegalArgumentException("Cannot create Instance for DetailViewModel class")
    }
}