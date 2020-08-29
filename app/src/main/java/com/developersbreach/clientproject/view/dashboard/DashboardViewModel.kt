package com.developersbreach.clientproject.view.dashboard
 
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.map
import com.developersbreach.clientproject.auth.AuthenticationState
import com.developersbreach.clientproject.auth.FirebaseUserLiveData
import com.developersbreach.clientproject.model.Dashboard

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    val authenticationState = FirebaseUserLiveData().map { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }

    private val _dashboardList = Dashboard.dashboardData(application.applicationContext)
    val dashboardList: List<Dashboard>
        get() = _dashboardList
}