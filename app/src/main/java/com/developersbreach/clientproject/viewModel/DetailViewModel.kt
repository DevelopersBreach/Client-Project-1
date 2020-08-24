package com.developersbreach.clientproject.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.developersbreach.clientproject.model.Customers

class DetailViewModel(
    application: Application,
    customers: Customers,
    photoUrl: String,
) : AndroidViewModel(application) {

    private val _selectedCustomer: Customers = customers
    val selectedCustomer: Customers
        get() = _selectedCustomer

    private val _selectedUser: String = photoUrl
    val selectedUser: String
        get() = _selectedUser
}