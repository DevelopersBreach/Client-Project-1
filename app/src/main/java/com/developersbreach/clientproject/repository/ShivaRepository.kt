package com.developersbreach.clientproject.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.developersbreach.clientproject.auth.AuthenticationState
import com.developersbreach.clientproject.auth.FirebaseUserLiveData
import com.developersbreach.clientproject.utils.COLLECTION_BILLS
import com.developersbreach.clientproject.utils.COLLECTION_CUSTOMER_BILL
import com.developersbreach.clientproject.utils.COLLECTION_SUBMISSIONS
import com.developersbreach.clientproject.utils.isNetworkConnected
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShivaRepository(
    private val context: Context,
) {

    fun getFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    fun getCurrentUserPhoneNumber(): String {
        return getFirebaseAuth().currentUser?.phoneNumber.toString()
    }

    private fun getInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    suspend fun getUserAccount(): CollectionReference {
        return withContext(Dispatchers.Unconfined) {
            getInstance().collection(COLLECTION_SUBMISSIONS)
        }
    }

    suspend fun submitBillToRootCollection(): CollectionReference {
        return withContext(Dispatchers.Unconfined) {
            getInstance().collection(COLLECTION_BILLS)
        }
    }

    suspend fun submitBillToCustomerCollection(phoneNumber: String): CollectionReference {
        return withContext(Dispatchers.Unconfined) {
            // /submissions/{7032000589}/customerSubmissions/bills/{2222}
            getUserAccount().document(phoneNumber).collection(COLLECTION_CUSTOMER_BILL)
        }
    }

    suspend fun getCurrentCustomerTotalBills(phoneNumber: String): Query {
        return withContext(Dispatchers.Unconfined) {
            getUserAccount().document(phoneNumber).collection(COLLECTION_CUSTOMER_BILL)
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