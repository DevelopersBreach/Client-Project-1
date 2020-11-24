package com.developersbreach.clientproject.view.editor

import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.model.Submission
import com.developersbreach.clientproject.utils.isNetworkConnected
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.DateFormat
import java.util.*


@BindingAdapter(
    "bindBillNumberEditText",
    "bindBillNumberInputLayout",
    "bindValidateExpectedDeliveryDate",
    "bindEditorViewModel"
)
fun MaterialButton.setConfirmNewSubmissionButton(
    billNumberEditText: TextInputEditText,
    billNumberInputLayout: TextInputLayout,
    validateExpectedDeliveryDate: TextView,
    viewModel: EditorViewModel
) {

    billNumberEditText.doAfterTextChanged { query ->
        validateField(query.toString(), billNumberInputLayout)
        performNullOrEmptyCheck(
            billNumberEditText.text.toString().length,
            validateExpectedDeliveryDate.text.toString(),
            this
        )
    }

    this.setOnClickListener {

        val billNumber = billNumberEditText.text.toString()
        val deliveryDate = validateExpectedDeliveryDate.text.toString()
        val submission = Submission(billNumber, deliveryDate)

        if (isNetworkConnected(context)) {
            submitDataToFirestore(viewModel, billNumber, submission)
        } else {
            showNetworkDialog(context)
        }
    }
}

private fun performNullOrEmptyCheck(
    billNumberEditText: Int,
    date: String,
    button: MaterialButton
) {
    if (billNumberEditText != 4 || date.isEmpty()) {
        button.visibility = View.INVISIBLE
    } else {
        button.visibility = View.VISIBLE
    }
}

private fun validateField(
    text: String,
    textInputLayout: TextInputLayout,
) {
    val context = textInputLayout.context
    if (text.isEmpty()) {
        textInputLayout.isErrorEnabled = true
        textInputLayout.error = context.getString(R.string.field_required_error_text)
    } else if (text.isNotEmpty() && text.length <= 3) {
        textInputLayout.isErrorEnabled = true
        textInputLayout.error = context.getString(R.string.field_length_error_text)
    } else if (text.isNotEmpty() && text.length == 4) {
        textInputLayout.isErrorEnabled = false
    }
}

private fun MaterialButton.submitDataToFirestore(
    viewModel: EditorViewModel,
    billNumber: String,
    submission: Submission
) {
    // /bills/{2222}
    viewModel.mainBillsCollection.document(billNumber).set(submission).addOnSuccessListener {

        // /submissions/{7032000589}/customerSubmissions/bills/{2222}
        viewModel.customerBillCollection.document(billNumber).set(submission).addOnSuccessListener {
            // Show only dialog once
            firestoreSuccessListener(billNumber)
            // Failure for subCollection bill submission
        }.addOnFailureListener { firestoreFailureListener() }
        // Failure for root or main bill submission
    }.addOnFailureListener { firestoreFailureListener() }
}

private fun MaterialButton.firestoreSuccessListener(
    billNumber: String,
) {
    MaterialAlertDialogBuilder(context)
        .setTitle(billNumber)
        .setMessage(context.getString(R.string.dialog_positive_message_text))
        .setPositiveButton(context.getString(R.string.dialog_positive_button_ok)) { dialog, _ ->
            dialog.dismiss()
            findNavController().navigateUp()
        }
        .show()
}

private fun MaterialButton.firestoreFailureListener() {
    val message = context.getString(R.string.customer_firestore_unsuccessful)
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

private fun showNetworkDialog(context: Context) {
    MaterialAlertDialogBuilder(context)
        .setTitle(context.getString(R.string.dialog_error_title_text))
        .setMessage(context.getString(R.string.dialog_error_message_text))
        .setPositiveButton(context.getString(R.string.dialog_positive_button_ok)) { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}


@BindingAdapter("bindExpectedDeliveryDateTextView")
fun TextView.setDeliveryDateTextView(
    viewModel: EditorViewModel,
) {
    val dayOfMonth = viewModel.date
    val month = viewModel.month
    val year = viewModel.year
    val deliveryDate = "${dayOfMonth + 1}/${month + 1}/$year"
    this.text = deliveryDate

    this.setOnClickListener {
        val datePickerDialog = DatePickerDialog(context, { _, i, i2, i3 ->
            onDateSet(i, i2, i3, viewModel, this)
        }, year, month, dayOfMonth)
        datePickerDialog.show()
    }
}

private fun onDateSet(
    year: Int,
    month: Int,
    dayOfMonth: Int,
    viewModel: EditorViewModel,
    textView: TextView,
) {
    viewModel.calendar.set(Calendar.YEAR, year)
    viewModel.calendar.set(Calendar.MONTH, month)
    viewModel.calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
    val format = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(viewModel.calendar.time)
    textView.text = format
}