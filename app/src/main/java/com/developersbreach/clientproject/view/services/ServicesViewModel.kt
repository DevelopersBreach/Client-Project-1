package com.developersbreach.clientproject.view.services

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.developersbreach.clientproject.model.Services

class ServicesViewModel(application: Application) : AndroidViewModel(application) {

    private val _servicesList = Services.servicesData(application.applicationContext)
    val servicesList: List<Services>
        get() = _servicesList
}