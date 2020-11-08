package com.developersbreach.clientproject.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.developersbreach.clientproject.auth.AuthenticationState
import com.developersbreach.clientproject.auth.FirebaseUserLiveData
import com.developersbreach.clientproject.utils.COLLECTION_NAME_CUSTOMERS
import com.developersbreach.clientproject.utils.isNetworkConnected
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShivaRepository(
    private val context: Context,
) {

    private fun getInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    suspend fun getSubmissions(): Query {
        return withContext(Dispatchers.Unconfined) {
            getInstance().collection(COLLECTION_NAME_CUSTOMERS)
        }
    }

    fun getAuthenticationState(): LiveData<AuthenticationState> {
        return FirebaseUserLiveData().map { user ->
            @Suppress("SENSELESS_COMPARISON")
            if (user != null) {
                AuthenticationState.AUTHENTICATED
            } else {
                AuthenticationState.UNAUTHENTICATED
            }
        }
    }

    fun ifConnected(): Boolean {
        return isNetworkConnected(context)
    }
}