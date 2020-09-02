package com.developersbreach.clientproject.view.billNumber

import androidx.lifecycle.ViewModel
import com.developersbreach.clientproject.R
import com.google.android.material.textfield.TextInputLayout

class BillNumberViewModel: ViewModel() {

    fun validateBillNumber(
        text: String?,
        textInputLayout: TextInputLayout
    ): String {
        textInputLayout.context
        if (text?.isEmpty()!!) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = textInputLayout.context.getString(R.string.field_required_error_text)
        } else if (text.isNotEmpty()) {
            textInputLayout.isErrorEnabled = false
        }

        return text.toString()
    }
}