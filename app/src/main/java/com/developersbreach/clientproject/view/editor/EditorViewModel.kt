package com.developersbreach.clientproject.view.editor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.developersbreach.clientproject.model.Submission
import com.developersbreach.clientproject.repository.ShivaRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.*

class EditorViewModel(
    application: Application,
    val submission: Submission?
) : AndroidViewModel(application) {

    private val repository = ShivaRepository(application.applicationContext)
    private var viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(
        viewModelJob + Dispatchers.Unconfined
    )

    lateinit var mainBillsCollection: CollectionReference
    lateinit var customerBillCollection: CollectionReference

    val calendar: Calendar = Calendar.getInstance()

    private var _currentDate: Int = 0
    val date: Int
        get() = _currentDate

    private var _currentMonth: Int = 0
    val month: Int
        get() = _currentMonth

    private var _currentYear: Int = 0
    val year: Int
        get() = _currentYear

    init {
        _currentDate = calendar.get(Calendar.DATE)
        _currentMonth = calendar.get(Calendar.MONTH)
        _currentYear = calendar.get(Calendar.YEAR)

        viewModelScope.launch {

            // /bills/{2222}
            mainBillsCollection = repository.submitBillToRootCollection()

            // Gets phone number of existing logged-in user
            val phoneNumber: String = repository.getCurrentUserPhoneNumber().drop(3)
            // /submissions/{7032000589}/customerSubmissions/bills/{2222}
            customerBillCollection = repository.submitBillToCustomerCollection(phoneNumber)
        }
    }
}