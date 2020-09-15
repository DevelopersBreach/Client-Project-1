package com.developersbreach.clientproject.view.billNumber

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.model.Customers
import com.developersbreach.clientproject.utils.COLLECTION_PATH
import com.developersbreach.clientproject.view.controller.billNumberToDashboard
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore


@BindingAdapter(
    "bindBillNumberEditText", "bindBillNumberInputLayout", "bindBillNumberSubmitButton",
    "bindBillNumberConstraintParent", "bindBillNumberProgressBarParent", "bindBillNumberLabelText"
)
fun TextInputEditText.setBillNumberEditText(
    viewModel: BillNumberViewModel,
    inputLayout: TextInputLayout,
    billNumberSubmit: Button,
    parent: ConstraintLayout,
    progressBar: FrameLayout,
    billNumberLabel: TextView
) {
    this.doAfterTextChanged { query ->
        val billNumber = viewModel.validateBillNumber(query.toString(), inputLayout)

        if (this.text.isNullOrEmpty() || billNumber.length < 4) {
            billNumberSubmit.isClickable = false
            billNumberSubmit.alpha = 0.2F
        } else {
            billNumberSubmit.isClickable = false
            billNumberSubmit.alpha = 1F
        }

        billNumberSubmit.setOnClickListener {
            if (billNumber.length == 4) {
                firestoreSearch(
                    billNumber,
                    findNavController(),
                    parent,
                    progressBar
                )
            }
        }
    }

    setOnTouchListener { view, _ ->
        view.performClick()
        billNumberLabel.visibility = View.GONE
        false
    }
}


private fun firestoreSearch(
    billNumber: String,
    navController: NavController,
    parent: ConstraintLayout,
    progressBar: FrameLayout
) {
    showProgress(parent, progressBar)
    FirebaseFirestore.getInstance()
        .collection(COLLECTION_PATH)
        .document(billNumber)
        .get()
        .addOnSuccessListener { document ->
            if (document.exists()) {
                val customer = document.toObject(Customers::class.java)
                hideProgress(parent, progressBar)
                billNumberToDashboard(navController, customer)
            } else {
                hideProgress(parent, progressBar)
                showCustomerNotFoundDialog(parent.context)
            }
        }
}

private fun showCustomerNotFoundDialog(context: Context) {
    MaterialAlertDialogBuilder(context, R.style.AppTheme_Customer_Dialog_Alert)
        .setTitle(context.getString(R.string.customer_not_found_error_title_text))
        .setMessage(context.getString(R.string.customer_not_found_error_message_text))
        .setPositiveButton(context.getString(R.string.customer_dialog_positive_button)) { dialog, _ ->
            dialog.dismiss()
        }
        .setCancelable(false)
        .show()
}

private fun showProgress(
    parent: ConstraintLayout,
    progressBar: FrameLayout
) {
    parent.visibility = View.INVISIBLE
    progressBar.visibility = View.VISIBLE
}

private fun hideProgress(
    parent: ConstraintLayout,
    progressBar: FrameLayout
) {
    parent.visibility = View.VISIBLE
    progressBar.visibility = View.INVISIBLE
}
