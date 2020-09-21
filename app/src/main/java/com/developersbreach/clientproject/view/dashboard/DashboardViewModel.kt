package com.developersbreach.clientproject.view.dashboard
 
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.map
import com.developersbreach.clientproject.auth.AuthenticationState
import com.developersbreach.clientproject.auth.FirebaseUserLiveData
import com.developersbreach.clientproject.model.Dashboard
import com.developersbreach.clientproject.utils.isNetworkConnected

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    private val _isConnected: Boolean = isNetworkConnected(application.applicationContext)
    val isConnected: Boolean
        get() = _isConnected

    private val _dashboardList = Dashboard.dashboardData(application.applicationContext)
    val dashboardList: List<Dashboard>
        get() = _dashboardList

    @Suppress("SENSELESS_COMPARISON")
    val authenticationState = FirebaseUserLiveData().map {user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }
}