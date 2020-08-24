package com.developersbreach.clientproject.view.billNumber

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.developersbreach.clientproject.R
import com.google.android.material.textfield.TextInputLayout

class BillNumberViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext

    fun validateBillNumber(
        text: String?,
        textInputLayout: TextInputLayout
    ): String {
        if (text?.isEmpty()!!) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = context.getString(R.string.field_required_error_text)
        } else if (text.isNotEmpty()) {
            textInputLayout.isErrorEnabled = false
        }

        return text.toString()
    }
}